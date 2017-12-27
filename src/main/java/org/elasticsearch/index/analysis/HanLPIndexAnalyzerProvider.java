package org.elasticsearch.index.analysis;

import com.hankcs.lucene.HanLPIndexAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public class HanLPIndexAnalyzerProvider extends AbstractIndexAnalyzerProvider<Analyzer> {
    private boolean enablePorterStemming;

    public HanLPIndexAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name, settings);
        this.enablePorterStemming = settings.getAsBoolean("enablePorterStemming", false);
    }

    @Override
    public Analyzer get() {
        return new HanLPIndexAnalyzer(enablePorterStemming);
    }

}
