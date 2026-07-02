package com.praj.newsaggregator.rss.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RssFeed {

    @JacksonXmlProperty(localName = "channel")
    private Channel channel;

}