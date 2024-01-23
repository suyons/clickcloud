package clickcloud.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class IndexController {
    // .properties 파일의 index.url 값을 필드 indexUrl에 주입
    @Value("${index.url}")
    private String indexUrl;

    // index.html 파일 읽어들여 내용을 반환
	@SuppressWarnings("null")
	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	public Mono<String> getIndex() {
		final WebClient client = WebClient.create();
		return client.get().uri(indexUrl)
				.retrieve().bodyToMono(String.class);
	}
}