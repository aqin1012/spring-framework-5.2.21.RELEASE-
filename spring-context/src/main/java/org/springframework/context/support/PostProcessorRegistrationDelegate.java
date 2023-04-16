/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.lang.Nullable;

/**
 * Delegate for AbstractApplicationContext's post-processor handling.
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 4.0
 */
final class PostProcessorRegistrationDelegate {

	private PostProcessorRegistrationDelegate() {
	}


	public static void invokeBeanFactoryPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {

		/** 传进来的beanFactoryPostProcessors默认空
		 * 无论什么情况，优先执行BeanDefinitionRegistryPostProcessors
		 * 将已经执行过的BFPP存储在processedBeans中 */
		// Invoke BeanDefinitionRegistryPostProcessors first, if any.
		Set<String> processedBeans = new HashSet<>();

		/**
		 * 判断beanFactory是否是BeanDefinitionRegistry类型
		 * 此处是DefaultListableBeanFactory
		 */
		if (beanFactory instanceof BeanDefinitionRegistry) {
			/** 类型转换 */
			BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
			/**
			 * 分别新建BeanFactoryPostProcessor和BeanDefinitionRegistryPostProcessor存放集合
			 * BeanDefinitionRegistryPostProcessor是BeanFactoryPostProcessor的子集
			 * 但是，BeanFactoryPostProcessor主要针对的操作对象是BeanFactory;BeanDefinitionRegistryPostProcessor主要针对的操作对象是BeanDefinition
			 * */
			List<BeanFactoryPostProcessor> regularPostProcessors = new ArrayList<>();
			List<BeanDefinitionRegistryPostProcessor> registryProcessors = new ArrayList<>();

			/**
			 * 遍历入参的beanFactoryPostProcessors
			 */
			for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessors) {
				/** 判断是否为BeanDefinitionRegistryPostProcessor */
				if (postProcessor instanceof BeanDefinitionRegistryPostProcessor) {
					BeanDefinitionRegistryPostProcessor registryProcessor =
							(BeanDefinitionRegistryPostProcessor) postProcessor;
					/** 先执行自己独有的，都有的postProcessBeanFactory()可以在后面一起执行 */
					registryProcessor.postProcessBeanDefinitionRegistry(registry);
					/** 用于后续执行postProcessBeanFactory */
					registryProcessors.add(registryProcessor);
				} else {
					/** 只是普通的BeanFactoryPostProcessor */
					regularPostProcessors.add(postProcessor);
				}
			}

			// Do not initialize FactoryBeans here: We need to leave all regular beans
			// uninitialized to let the bean factory post-processors apply to them!
			// Separate between BeanDefinitionRegistryPostProcessors that implement
			// PriorityOrdered, Ordered, and the rest.
			/** 用于保存本次要执行的BeanDefinitionRegistry */
			List<BeanDefinitionRegistryPostProcessor> currentRegistryProcessors = new ArrayList<>();

			// First, invoke the BeanDefinitionRegistryPostProcessors that implement PriorityOrdered.
			/**
			 * 找到所有实现PriorityOrdered接口的BeanDefinitionRegistryPostProcessor的实现类的beanName
			 */
			String[] postProcessorNames =
					beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			/** 遍历处理所有符合规矩的postProcessorNames */
			for (String ppName : postProcessorNames) {
				/** 检测是否实现了PriorityOrdered接口 */
				if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
					/** 获取名字对应的bean实例，添加到currentRegistryProcessors中 */
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					/** 添加进集合，避免重复执行 */
					processedBeans.add(ppName);
				}
			}

			/** 按照优先级进行排序 */
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			/** 用于最后执行postProcessBeanFactory */
			registryProcessors.addAll(currentRegistryProcessors);
			/** 遍历currentRegistryProcessors执行postProcessBeanDefinitionRegistry */
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			/** 执行完毕后，清空～ */
			currentRegistryProcessors.clear();

			// Next, invoke the BeanDefinitionRegistryPostProcessors that implement Ordered.
			/** 将PostProcessor分为三种方式，分别进行处理 */
			postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			for (String ppName : postProcessorNames) {
				/** 检查是否实现了Ordered接口（未执行过的）*/
				if (!processedBeans.contains(ppName) && beanFactory.isTypeMatch(ppName, Ordered.class)) {
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					/** 将已经执行过的BFPP记录下来，避免后续重复执行（空间换时间） */
					processedBeans.add(ppName);
				}
			}
			/** 按照优先级来执行 */
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			registryProcessors.addAll(currentRegistryProcessors);
			/**
			 * 遍历集合，执行postProcessBeanDefinitionRegistry方法
			 */
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			/** 执行完清空 */
			currentRegistryProcessors.clear();

			// Finally, invoke all other BeanDefinitionRegistryPostProcessors until no further ones appear.
			/** 最后，调用所有剩下的BeanDefinitionRegistryPostProcessor*/
			boolean reiterate = true;
			while (reiterate) {
				reiterate = false;
				postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
				for (String ppName : postProcessorNames) {
					if (!processedBeans.contains(ppName)) {
						/** 跳过已经执行过的，将其余的添加进集合 */
						currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
						processedBeans.add(ppName);
						reiterate = true;
					}
				}
				sortPostProcessors(currentRegistryProcessors, beanFactory);
				/** 用于最后执行postProcessBeanFactory */
				registryProcessors.addAll(currentRegistryProcessors);
				invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
				currentRegistryProcessors.clear();
			}
			/**
			 * 至此，实现了PriorityOrdered，或者实现了Ordered，或者都没实现的都处理完了
			 */

			// Now, invoke the postProcessBeanFactory callback of all processors handled so far.
			/** 分别调用两个集合的postProcessBeanFactory方法 */
			invokeBeanFactoryPostProcessors(registryProcessors, beanFactory);
			invokeBeanFactoryPostProcessors(regularPostProcessors, beanFactory);
		} else {
			// Invoke factory processors registered with the context instance.
			/**
			 * 如果BeanFactory不归属BeanDefinitionRegistry类型，那么直接执行postProcessBeanFactory()方法
			 */
			invokeBeanFactoryPostProcessors(beanFactoryPostProcessors, beanFactory);
		}

		/**
		 * 至此入参的BeanFactoryPostProcessor和容器中的所有BeanDefinitionRegistryPostProcessor都已经处理完毕
		 * 下面开始处理所有的BeanFactoryPostProcessor，即会存在一些类，只实现了BeanFactoryPostProcessor接口
		 */

		// Do not initialize FactoryBeans here: We need to leave all regular beans
		// uninitialized to let the bean factory post-processors apply to them!
		/** 找到所有实现BeanFactoryPostProcessor接口的类 */
		String[] postProcessorNames =
				beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);

		// Separate between BeanFactoryPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		/**
		 * 实现不同接口的放进不同的集合里
		 */
		/** 存放实现了PriorityOrdered接口的BeanFactoryPostProcessor */
		List<BeanFactoryPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
		/** 存放实现了Ordered接口的BeanFactoryPostProcessor的BeanName */
		List<String> orderedPostProcessorNames = new ArrayList<>();
		/** 存放普通的BeanFactoryPostProcessor的BeanName */
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();
		for (String ppName : postProcessorNames) {
			if (processedBeans.contains(ppName)) {
				/** 跳过已经执行过的BeanFactoryPostProcessor，
				 * 并根据实现不同的排序接口将这些PostProcessor分别放进不同的集合中去 */
				// skip - already processed in first phase above
			} else if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				priorityOrderedPostProcessors.add(beanFactory.getBean(ppName, BeanFactoryPostProcessor.class));
			} else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				orderedPostProcessorNames.add(ppName);
			} else {
				nonOrderedPostProcessorNames.add(ppName);
			}
		}
		/**
		 * 添加完成后，分别进行操作
		 */
		// First, invoke the BeanFactoryPostProcessors that implement PriorityOrdered.
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);

		// Next, invoke the BeanFactoryPostProcessors that implement Ordered.
		List<BeanFactoryPostProcessor> orderedPostProcessors = new ArrayList<>(orderedPostProcessorNames.size());
		for (String postProcessorName : orderedPostProcessorNames) {
			orderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		sortPostProcessors(orderedPostProcessors, beanFactory);
		invokeBeanFactoryPostProcessors(orderedPostProcessors, beanFactory);

		// Finally, invoke all other BeanFactoryPostProcessors.
		List<BeanFactoryPostProcessor> nonOrderedPostProcessors = new ArrayList<>(nonOrderedPostProcessorNames.size());
		for (String postProcessorName : nonOrderedPostProcessorNames) {
			nonOrderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		invokeBeanFactoryPostProcessors(nonOrderedPostProcessors, beanFactory);

		// Clear cached merged bean definitions since the post-processors might have
		// modified the original metadata, e.g. replacing placeholders in values...
		beanFactory.clearMetadataCache();
	}

	public static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, AbstractApplicationContext applicationContext) {
		/** 找到所有的实现了BeanPostProcessor接口的类（去工厂里根据类型扫描） */
		String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);

		// Register BeanPostProcessorChecker that logs an info message when
		// a bean is created during BeanPostProcessor instantiation, i.e. when
		// a bean is not eligible for getting processed by all BeanPostProcessors.
		/**
		 * 记录下BeanPostProcessor的目标计数
		 * 此处+1的原因：在此方法的最后会添加一个BeanPostProcessorChecker的类
		 * */
		int beanProcessorTargetCount = beanFactory.getBeanPostProcessorCount() + 1 + postProcessorNames.length;
		/** 添加BeanPostProcessorChecker(主要用于记录信息)到beanFactory */
		beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));

		// Separate between BeanPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		/**
		 * 定义集合
		 */
		/** 存放实现了PriorityOrdered接口的BeanPostProcessor */
		List<BeanPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
		/** 存放实现了MergedBeanDefinitionPostProcessor接口的BeanPostProcessor */
		List<BeanPostProcessor> internalPostProcessors = new ArrayList<>();
		/** 存放实现了Ordered接口的BeanPostProcessor */
		List<String> orderedPostProcessorNames = new ArrayList<>();
		/** 存放普通的BeanPostProcessor的name集合 */
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();
		/**
		 * 如果ppName对应的BeanPostProcessor实例实现了PriorityOrdered接口口
		 * 则捕获到ppName对应的BeanPostProcessor的实例添加到priorityOrderedPostProcessors中
		 * */
		for (String ppName : postProcessorNames) {
			if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
				priorityOrderedPostProcessors.add(pp);
				/**
				 * 如果ppName对应的BeanPostProcessor实例也实现了MergedBeanDefinitionPostProcessor接口
				 * 则捕获到ppName对应的BeanPostProcessor的实例添加到internalPostProcessors中
				 * */
				if (pp instanceof MergedBeanDefinitionPostProcessor) {
					internalPostProcessors.add(pp);
				}
				/**
				 * 如果ppName对应的BeanPostProcessor实例没有实现PriorityOrdered接口，但是实现了Ordered接口
				 * 则捕获到ppName对应的BeanPostProcessor的实例添加到orderedPostProcessorNames中
				 * */
			} else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				orderedPostProcessorNames.add(ppName);
			} else {
				/** 否则将ppName添加到nonOrderedPostProcessorNames */
				nonOrderedPostProcessorNames.add(ppName);
			}
		}

		// First, register the BeanPostProcessors that implement PriorityOrdered.
		/**
		 * 处理实现了PriorityOrdered接口的BeanPostProcessor
		 */
		/** 排序 */
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		/** 注册+添加到beanFactory */
		registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors);

		// Next, register the BeanPostProcessors that implement Ordered.
		/**
		 * 处理实现了Ordered接口的BeanPostProcessor
		 */
		List<BeanPostProcessor> orderedPostProcessors = new ArrayList<>(orderedPostProcessorNames.size());
		for (String ppName : orderedPostProcessorNames) {
			/** 根据ppName找到对应的BeanPostProcessor实例对象 */
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			/** 实现了Ordered接口的BeanPostProcessor添加进orderedPostProcessors中 */
			orderedPostProcessors.add(pp);
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				/**
				 * 如果ppName对应的BeanPostProcessor实例也实现了MergedBeanDefinitionPostProcessor接口
				 * 则捕获到ppName对应的BeanPostProcessor的实例添加到internalPostProcessors中
				 * */
				internalPostProcessors.add(pp);
			}
		}
		/** 排序 */
		sortPostProcessors(orderedPostProcessors, beanFactory);
		/** 注册+添加到beanFactory */
		registerBeanPostProcessors(beanFactory, orderedPostProcessors);

		// Now, register all regular BeanPostProcessors.
		/**
		 * 处理未实现了PriorityOrdered接口和Ordered接口（其余的）的BeanPostProcessor
		 */
		List<BeanPostProcessor> nonOrderedPostProcessors = new ArrayList<>(nonOrderedPostProcessorNames.size());
		for (String ppName : nonOrderedPostProcessorNames) {
			/** 根据ppName找到对应的BeanPostProcessor实例对象 */
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			/** 这些这些BeanPostProcessor添加进orderedPostProcessors中 */
			nonOrderedPostProcessors.add(pp);
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				/**
				 * 如果ppName对应的BeanPostProcessor实例也实现了MergedBeanDefinitionPostProcessor接口
				 * 则捕获到ppName对应的BeanPostProcessor的实例添加到internalPostProcessors中
				 * */
				internalPostProcessors.add(pp);
			}
		}
		/** 注册+添加到beanFactory */
		registerBeanPostProcessors(beanFactory, nonOrderedPostProcessors);

		// Finally, re-register all internal BeanPostProcessors.
		/** 将所有实现了MergedBeanDefinitionPostProcessor接口的BeanPostProcessor的实例排序 */
		sortPostProcessors(internalPostProcessors, beanFactory);
		/** 注册+添加到beanFactory */
		registerBeanPostProcessors(beanFactory, internalPostProcessors);

		// Re-register post-processor for detecting inner beans as ApplicationListeners,
		// moving it to the end of the processor chain (for picking up proxies etc).
		/** 注册ApplicationListenerDetector到BeanFactory中 */
		beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
	}

	private static void sortPostProcessors(List<?> postProcessors, ConfigurableListableBeanFactory beanFactory) {
		// Nothing to sort?
		if (postProcessors.size() <= 1) {
			return;
		}
		Comparator<Object> comparatorToUse = null;
		if (beanFactory instanceof DefaultListableBeanFactory) {
			comparatorToUse = ((DefaultListableBeanFactory) beanFactory).getDependencyComparator();
		}
		if (comparatorToUse == null) {
			/** 会获取到一个默认的比较器 */
			comparatorToUse = OrderComparator.INSTANCE;
		}
		postProcessors.sort(comparatorToUse);
	}

	/**
	 * Invoke the given BeanDefinitionRegistryPostProcessor beans.
	 */
	private static void invokeBeanDefinitionRegistryPostProcessors(
			Collection<? extends BeanDefinitionRegistryPostProcessor> postProcessors, BeanDefinitionRegistry registry) {

		for (BeanDefinitionRegistryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanDefinitionRegistry(registry);
		}
	}

	/**
	 * Invoke the given BeanFactoryPostProcessor beans.
	 */
	private static void invokeBeanFactoryPostProcessors(
			Collection<? extends BeanFactoryPostProcessor> postProcessors, ConfigurableListableBeanFactory beanFactory) {

		for (BeanFactoryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanFactory(beanFactory);
		}
	}

	/**
	 * Register the given BeanPostProcessor beans.
	 */
	private static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanPostProcessor> postProcessors) {

		for (BeanPostProcessor postProcessor : postProcessors) {
			beanFactory.addBeanPostProcessor(postProcessor);
		}
	}


	/**
	 * BeanPostProcessor that logs an info message when a bean is created during
	 * BeanPostProcessor instantiation, i.e. when a bean is not eligible for
	 * getting processed by all BeanPostProcessors.
	 */
	private static final class BeanPostProcessorChecker implements BeanPostProcessor {

		private static final Log logger = LogFactory.getLog(BeanPostProcessorChecker.class);

		private final ConfigurableListableBeanFactory beanFactory;

		private final int beanPostProcessorTargetCount;

		public BeanPostProcessorChecker(ConfigurableListableBeanFactory beanFactory, int beanPostProcessorTargetCount) {
			this.beanFactory = beanFactory;
			this.beanPostProcessorTargetCount = beanPostProcessorTargetCount;
		}

		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) {
			return bean;
		}

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) {
			if (!(bean instanceof BeanPostProcessor) && !isInfrastructureBean(beanName) &&
					this.beanFactory.getBeanPostProcessorCount() < this.beanPostProcessorTargetCount) {
				if (logger.isInfoEnabled()) {
					logger.info("Bean '" + beanName + "' of type [" + bean.getClass().getName() +
							"] is not eligible for getting processed by all BeanPostProcessors " +
							"(for example: not eligible for auto-proxying)");
				}
			}
			return bean;
		}

		private boolean isInfrastructureBean(@Nullable String beanName) {
			if (beanName != null && this.beanFactory.containsBeanDefinition(beanName)) {
				BeanDefinition bd = this.beanFactory.getBeanDefinition(beanName);
				return (bd.getRole() == RootBeanDefinition.ROLE_INFRASTRUCTURE);
			}
			return false;
		}
	}

}
