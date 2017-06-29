package com.test.docker;
import org.junit.Test;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.AuthConfig;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.PullImageResultCallback;
import com.github.dockerjava.core.command.PushImageResultCallback;

public class TestNewHarbor {
	
	@Test
	public void test691push() {
		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
			    .withDockerHost("tcp://10.45.80.21:2375")
//			    .withDockerTlsVerify(true)
//			    .withDockerCertPath("/home/user/.docker/certs")
//			    .withDockerConfig("/home/user/.docker")
			    .withApiVersion("1.23")
			    .withRegistryUrl("10.45.6.91")
			    .withRegistryUsername("admin")
			    .withRegistryPassword("Harbor12345")
//			    .withRegistryEmail("dockeruser@github.com")
			    .build();
			DockerClient docker = DockerClientBuilder.getInstance(config).build();
			try {
				AuthConfig authConfig = new AuthConfig();
				authConfig.withRegistryAddress("10.45.6.91").withUsername("admin").withPassword("Harbor12345");
				docker.pushImageCmd("")
				.withName("10.45.6.91/hotbilling/nginx")
				.withTag("1.10.3").withAuthConfig(authConfig)
				.exec(new PushImageResultCallback()).awaitSuccess();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*pushcmd.withTag("3.2.8");
			String tag = pushcmd.getTag();
			String name = pushcmd.getName();
			System.out.println(name + tag);*/
	}
	
	@Test
	public void test691pull() {
		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
			    .withDockerHost("tcp://10.45.80.21:2375")
//			    .withDockerTlsVerify(true)
//			    .withDockerCertPath("/home/user/.docker/certs")
//			    .withDockerConfig("/home/user/.docker")
			    .withApiVersion("1.23")
			    .withRegistryUrl("10.45.6.91")
			    .withRegistryUsername("admin")
			    .withRegistryPassword("Harbor12345")
//			    .withRegistryEmail("dockeruser@github.com")
			    .build();
			DockerClient docker = DockerClientBuilder.getInstance(config).build();
			try {
				//10.45.6.91/hotbilling/nginx:1.10.3
				//10.45.6.91/hotbilling/nginx:1.10.3
				AuthConfig authConfig = new AuthConfig();
				authConfig.withRegistryAddress("10.45.6.91").withUsername("admin").withPassword("Harbor12345");
				docker.pullImageCmd("10.45.6.91/hotbilling/bom")
				.withTag("20170511_20170612")
				.withAuthConfig(authConfig)
				.exec(new PullImageResultCallback()).awaitSuccess();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*pushcmd.withTag("3.2.8");
			String tag = pushcmd.getTag();
			String name = pushcmd.getName();
			System.out.println(name + tag);*/
	}
}
