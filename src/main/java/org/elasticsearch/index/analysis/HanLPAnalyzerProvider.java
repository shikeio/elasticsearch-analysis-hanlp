package org.elasticsearch.index.analysis;

import com.hankcs.lucene.HanLPAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public class HanLPAnalyzerProvider extends AbstractIndexAnalyzerProvider<Analyzer> {
    private boolean enablePorterStemming;

    public HanLPAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name, settings);
        this.enablePorterStemming = settings.getAsBoolean("enablePorterStemming", false);
    }

    @Override
    public Analyzer get() {
        return new HanLPAnalyzer(enablePorterStemming);
    }

}
