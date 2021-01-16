package com.lvchao.common.enum1;

public enum ConfirmType {

		/**
		 * Use {@code RabbitTemplate#waitForConfirms()} (or {@code waitForConfirmsOrDie()}
		 * within scoped operations.
		 */
		SIMPLE,

		/**
		 * Use with {@code CorrelationData} to correlate confirmations with sent
		 * messsages.
		 */
		CORRELATED,

		/**
		 * Publisher confirms are disabled (default).
		 */
		NONE

	}