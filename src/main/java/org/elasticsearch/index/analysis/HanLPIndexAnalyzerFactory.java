package org.elasticsearch.index.analysis;

import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.lucene.HanLPTokenizer;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-06-26.
 */
public class HanLPIndexAnalyzerFactory extends AbstractTokenizerFactory {

    public HanLPIndexAnalyzerFactory(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        super(indexSettings, name, settings);
    }

    @Override
    public Tokenizer create() {
        return new HanLPTokenizer(IndexTokenizer.SEGMENT, null, false);
    }
}
