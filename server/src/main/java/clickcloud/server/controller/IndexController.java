package clickcloud.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

//html 파일 불러와서 뷰에 전달

@RestController
public class IndexController {
		//.properties 파일의 index.url값을 필드 indexUrl에 주입
    @Value("${index.url}")
    private String indexUrl;

	//index.html 파일 가져와서 뷰에 전달하는 api
	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE) //해당 경로에 GET(조회) 요청
	public Mono<String> getIndex() { //Mono<String> 타입
		final WebClient client = WebClient.create();
		return client.get().uri(indexUrl)
				.retrieve().bodyToMono(String.class);
	}

	

}