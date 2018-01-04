> Important

Thanks the great projects:
1. [**lucene**](https://github.com/apache/lucene-solr)
2. [**elasticsearch**](https://github.com/elastic/elasticsearch)
3. [**HanLP**](https://github.com/hankcs/HanLP)

# Install lib
 ```bash
> cd ${projecthome}/libs
> mvn install:install-file -Dfile=hanlp-${version}.jar -DgroupId=com.hankcs -DartifactId=hanlp -Dversion=${version} -Dpackaging=jar
```

# Import HanLP data

1. Download HanLP data.See here [HanLP Releases](https://github.com/hankcs/HanLP/releases)
2. Modify the data root in [config](config/hanlp.properties), change the ${data.root} to your own HanLP root data dir
