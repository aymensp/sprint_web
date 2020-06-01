package com.codename1.braintree;


/**
 *  It's not good practice to call the native interface directly, this class 
 *  hides some of the low level implementation details if any.
 */
public class Purchase {

	public Purchase() {
	}

	public static void startOrder(Purchase.Callback callback) {
	}

	public static interface class Callback {


		public String fetchToken() {
		}

		public void onPurchaseSuccess(String nonce) {
		}

		public void onPurchaseFail(String errorMessage) {
		}

		public void onPurchaseCancel() {
		}
	}
}
