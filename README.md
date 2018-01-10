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

# Index and Highlight

## Mapping


`PUT test/_mapping/test`
```json
{
  "properties": {
    "content": {
      "type": "text",
      "analyzer": "hanlp-index",
      "search_analyzer": "hanlp-index",
      "index_options": "offsets"
    }
  }
}
```

## Index Document

PUT /test/test/1
```json
{
  "content": ["中华人民共和国","地大物博"]
}
```

## Highlight

POST /test/test/_search
```json
{
  "query": {
    "match": {
      "content": "中华"
    }
  },
  "highlight": {
    "pre_tags": [
      "<tag1>"
    ],
    "post_tags": [
      "</tag1>"
    ],
    "fields": {
      "content": {}
    }
  }
}
```


