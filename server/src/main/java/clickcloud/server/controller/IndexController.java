package clickcloud.server.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.cdimascio.dotenv.Dotenv;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {
	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	public Mono<String> getIndex() {
		Dotenv env = Dotenv.load();
		final WebClient client = WebClient.create();
		return client.get().uri(env.get("INDEX_URI"))
				.retrieve().bodyToMono(String.class);
	}
}