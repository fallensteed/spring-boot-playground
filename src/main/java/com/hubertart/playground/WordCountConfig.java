package com.hubertart.playground;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@ConfigurationProperties("word-count")
public class WordCountConfig {
    private boolean caseSensitive;
    private Words skipWords;

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public Words getSkipWords() {
        return skipWords;
    }

    public void setSkipWords(Words skipWords) {
        this.skipWords = skipWords;
    }

    public static class Words {
        private String[] skip;

        public String[] getSkip() {
            return skip;
        }

        public void setSkip(String[] skip) {
            this.skip = skip;
        }
    }
}
