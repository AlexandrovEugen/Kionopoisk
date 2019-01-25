package ru.sberbank.pao.services.impl;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.sberbank.pao.dto.MovieDTO;
import ru.sberbank.pao.services.api.Scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class KinopoiskScraper implements Scraper {


    private Optional<Document> getDocument() {

        Document document = null;
        try {
            document = Jsoup.connect("https://www.kinopoisk.ru/top/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(document);
    }


    @Override
    public List<MovieDTO> getTop() {
        return getDocument().orElseThrow(NullPointerException::new)
                .select("table.js-rum-hero tr")
                .stream()
                .skip(2)
                .map(this::toMovieDTO)
                .collect(Collectors.toList());
    }

    private MovieDTO toMovieDTO(Element e) {

        final List<Element> elements = new ArrayList<>(e.getElementsByTag("td"));

        return new MovieDTO(extractPosition(elements.get(0)),
                extractName(elements.get(1)),
                extractOriginalName(elements.get(1)),
                extractRate(elements.get(2)),
                extractVotesAmount(elements.get(2)),
                extractReleaseDate(elements.get(1)));
    }

    private String extractReleaseDate(Element element) {
        final String textFromTagA = getTextFromTagA(element);
        return textFromTagA.substring(textFromTagA.indexOf("(") + 1,
                textFromTagA.indexOf(")"));
    }

    private String extractVotesAmount(Element element) {
        return extract(element, 1);
    }

    private String extractRate(Element element) {
        return extract(element, 0);
    }

    private String extract(Element element, int position) {
        return element.text()
                .replace("(", ")")
                .split(Pattern.quote(")"))[position];
    }

    private String extractOriginalName(Element element) {
        final Element originalNameElement = element.getElementsByTag("splan").first();
        return originalNameElement == null ? "" : originalNameElement.text();
    }

    private String extractName(Element element) {
        final String nameWithDate = getTextFromTagA(element);
        return nameWithDate.substring(0, Objects.requireNonNull(nameWithDate).indexOf("("));
    }

    private String getTextFromTagA(Element element) {
        return element.getElementsByTag("a").first().text();
    }

    private Integer extractPosition(Element element) {
        return Integer.valueOf(element.text().replace(".", ""));
    }

}
