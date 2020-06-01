package com.codename1.braintree.impl;


/**
 *  This class receives callback from native off of the EDT!
 *  
 *  @deprecated this class is an implementation detail used internally
 */
public class BraintreePaymentCallback {

	public static com.codename1.braintree.Purchase.Callback cb;

	public BraintreePaymentCallback() {
	}

	public static void onPurchaseSuccess(String nonce) {
	}

	public static void onPurchaseFail(String errorMessage) {
	}

	public static void onPurchaseCancel() {
	}
}
