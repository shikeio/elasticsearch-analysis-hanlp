package org.elasticsearch.plugin.analysis;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.utility.Predefine;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.HanLPAnalyzerProvider;
import org.elasticsearch.index.analysis.HanLPIndexAnalyzerFactory;
import org.elasticsearch.index.analysis.HanLPIndexAnalyzerProvider;
import org.elasticsearch.index.analysis.HanLPTokenizerFactory;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AnalysisHanLPPlugin extends Plugin implements AnalysisPlugin {

    public static Set<String> defaultStopWordDictionary = new HashSet<>();

    static {
        Predefine.HANLP_PROPERTIES_PATH = System.getProperties().get("user.dir") + "/config/analysis-hanlp/hanlp.properties";
        HanLP.Config.enableDebug(false);
        defaultStopWordDictionary.addAll(IOUtil.readLineListWithLessMemory(HanLP.Config.CoreStopWordDictionaryPath));
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
        HashMap<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> tokenizers = new HashMap<>();
        tokenizers.put("hanlp", HanLPTokenizerFactory::createStandard);
        tokenizers.put("hanlp-standard", HanLPTokenizerFactory::createStandard);
        tokenizers.put("hanlp-nlp", HanLPTokenizerFactory::createNLP);
        tokenizers.put("hanlp-index", HanLPIndexAnalyzerFactory::new);
        tokenizers.put("hanlp-nshort", HanLPTokenizerFactory::createNShort);
        tokenizers.put("hanlp-shortest", HanLPTokenizerFactory::createShortest);
        tokenizers.put("hanlp-crf", HanLPTokenizerFactory::createCRF);
        tokenizers.put("hanlp-speed", HanLPTokenizerFactory::createSpeed);
        return tokenizers;
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> analyzers = new HashMap<>();
        analyzers.put("hanlp", HanLPAnalyzerProvider::new);
        analyzers.put("hanlp-index", HanLPIndexAnalyzerProvider::new);
        return analyzers;
    }

}
