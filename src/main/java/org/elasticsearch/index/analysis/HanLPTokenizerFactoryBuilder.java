package org.elasticsearch.index.analysis;//package org.elasticsearch.index.analysis;
//
//import com.hankcs.hanlp.seg.Segment;
//import com.hankcs.hanlp.tokenizer.StandardTokenizer;
//import com.hankcs.lucene.HanLPTokenizer;
//import org.apache.lucene.analysis.Tokenizer;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.env.Environment;
//import org.elasticsearch.index.IndexSettings;
//
///**
// * Created by yangbajing(yangbajing@gmail.com) on 2017-04-14.
// */
//
//class AbstractHanLPTokenizerFactory extends AbstractTokenizerFactory {
//
//    private boolean enablePorterStemming;
//
//    public AbstractHanLPTokenizerFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
//        super(indexSettings, name, settings);
//        this.enablePorterStemming = settings.getAsBoolean("enablePorterStemming", false);
//    }
//
//    @Override
//    public Tokenizer create() {
//        return new HanLPTokenizer(StandardTokenizer.SEGMENT, null, enablePorterStemming);
//    }
//
//}
//
//public class HanLPTokenizerFactoryBuilder {
//    public static HanLPTokenizerFactory createFactory(Segment seg) {
//    }
//}
