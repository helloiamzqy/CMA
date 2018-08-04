package jersey;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Component("jerseyPoolingClient")
public class JerseyPoolingClientFactoryBean implements FactoryBean<Client>, InitializingBean, DisposableBean {

	private Client client;

	private int maxTotal = 2000;

	private int defaultMaxPerRoute = 1000;

	private ClientConfig clientConfig;

	public JerseyPoolingClientFactoryBean() {
	}

	public JerseyPoolingClientFactoryBean(ClientConfig clientConfig) {
		this.clientConfig = clientConfig;
	}

	public JerseyPoolingClientFactoryBean(int maxTotal, int defaultMaxPerRoute) {
		this.maxTotal = maxTotal;
		this.defaultMaxPerRoute = defaultMaxPerRoute;
	}

	@Override
	public void destroy() throws Exception {
		this.client.close();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.clientConfig == null) {
			final ClientConfig clientConfig = new ClientConfig();
			PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager();
			pcm.setMaxTotal(this.maxTotal);
			pcm.setDefaultMaxPerRoute(this.defaultMaxPerRoute);

			clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER, pcm);

		      clientConfig.connectorProvider(new ApacheConnectorProvider());
			client = ClientBuilder.newClient(clientConfig);
		} else {

			client = ClientBuilder.newClient(this.clientConfig);
		}
	}

	@Override
	public Client getObject() throws Exception {
		if (null == this.client) {
			return ClientBuilder.newClient();
		}
		return this.client;
	}

	@Override
	public Class<?> getObjectType() {
		return (this.client == null ? Client.class : this.client.getClass());
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}