> Important

Thanks the great projects:
1. [**lucene**](https://github.com/apache/lucene-solr)
2. [**elasticsearch**](https://github.com/elastic/elasticsearch)
3. [**HanLP**](https://github.com/hankcs/HanLP)

# Build

## Install lib
 ```bash
gradle mvn
```

## Import HanLP data

1. Download HanLP data.See here [HanLP Releases](https://github.com/hankcs/HanLP/releases)
2. Modify the data root in [config](config/hanlp.properties), change the ${data.root} to your own HanLP root data dir

# Notice

Package `com.hankcs.lucene` copy from [hanlp-lucene-plugin](https://github.com/hankcs/hanlp-lucene-plugin)
