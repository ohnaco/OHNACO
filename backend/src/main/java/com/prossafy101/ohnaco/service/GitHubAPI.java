package com.prossafy101.ohnaco.service;

import com.prossafy101.ohnaco.entity.todo.GitHub;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GitHubAPI {
    private final WebClient webClient;

    public GitHubAPI(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").defaultHeader("Accept", "application/vnd.github.cloak-preview+json").build();
    }

    public int isCommit(String githubid, String date) {
        GitHub commitCount =
                this.webClient.get().uri(uriBuilder -> uriBuilder.path("/search/commits")
                .queryParam("q", "author:"+githubid+ " author-date:"+date).build())
                        .retrieve().bodyToMono(GitHub.class).block();
//        commitCount.subscribe(response -> {
//            System.out.println("response : " + response.getTotal_count());
//        }, e -> {
//            System.out.println("error message : " + e.getMessage());
//        });
        System.out.println(commitCount.getTotal_count());
        return commitCount.getTotal_count();
    }

//    public void test() {
//        WebClient webClient = WebClient.create();
//        GitHub responseJson = webClient.get()
//                .uri("https://api.github.com/search/commits?q=author:Changhwan-Son author-date:2021-07-17")
//                .header("Accept", "application/vnd.github.cloak-preview+json")
//                .retrieve()
//                .bodyToMono(GitHub.class)
//                .block();
//        System.out.println(responseJson.getTotal_count());
//
//    }
}
