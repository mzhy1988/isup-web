package com.hns.iusp.ws;

public interface ServiceProvider {
	<T> T getService(Class<T> T);
}
