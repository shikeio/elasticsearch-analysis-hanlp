> Important

Thanks the great projects:
1. [**lucene**](https://github.com/apache/lucene-solr)
2. [**elasticsearch**](https://github.com/elastic/elasticsearch)
3. [**HanLP**](https://github.com/hankcs/HanLP)

> Package `com.hankcs.lucene` copy from [hanlp-lucene-plugin](https://github.com/hankcs/hanlp-lucene-plugin)

# Build and Install

## Install lib
 ```bash
gradle mvn
```

## Import HanLP data

1. Download HanLP data.See here [HanLP Releases](https://github.com/hankcs/HanLP/releases)
2. Modify the data root in [config](config/hanlp.properties), change the ${data.root} to your own HanLP root data dir

## Modify Plugin Security Policy

Modify ${elasticsearchHome}/config/jvm.options add this in the end

```
-Djava.security.policy=file://${elasticsearchHome}/plugins/analysis-hanlp/plugin-security.policy
```

# Index and Highlight

Support two kind analyzer:
1. `HanLPAnalyzer` standard analyzer, alias `hanlp`
2. `HanLPIndexAnalyzer` index analyzer, alias `hanlp-index`

## Test Analyzer

`GET /_analyze`
```json
{
  "analyzer" : "hanlp-index",
  "text": ["中华人民共和国","地大物博"]
}
```

Response is:
```json
{
  "tokens": [
    {
      "token": "中华人民共和国",
      "start_offset": 0,
      "end_offset": 7,
      "type": "ns",
      "position": 0
    },
    {
      "token": "中华人民",
      "start_offset": 0,
      "end_offset": 4,
      "type": "nz",
      "position": 1
    },
    {
      "token": "中华",
      "start_offset": 0,
      "end_offset": 2,
      "type": "nz",
      "position": 2
    },
    {
      "token": "华人",
      "start_offset": 1,
      "end_offset": 3,
      "type": "n",
      "position": 3
    },
    {
      "token": "人民共和国",
      "start_offset": 2,
      "end_offset": 7,
      "type": "nz",
      "position": 4
    },
    {
      "token": "人民",
      "start_offset": 2,
      "end_offset": 4,
      "type": "n",
      "position": 5
    },
    {
      "token": "共和国",
      "start_offset": 4,
      "end_offset": 7,
      "type": "n",
      "position": 6
    },
    {
      "token": "共和",
      "start_offset": 4,
      "end_offset": 6,
      "type": "n",
      "position": 7
    },
    {
      "token": "地大物博",
      "start_offset": 8,
      "end_offset": 12,
      "type": "nz",
      "position": 8
    },
    {
      "token": "地大",
      "start_offset": 8,
      "end_offset": 10,
      "type": "nz",
      "position": 9
    }
  ]
}
```

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

Response is:
```json
{
  "took": 384,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "skipped": 0,
    "failed": 0
  },
  "hits": {
    "total": 1,
    "max_score": 0.2876821,
    "hits": [
      {
        "_index": "test",
        "_type": "test",
        "_id": "1",
        "_score": 0.2876821,
        "_source": {
          "content": [
            "中华人民共和国",
            "地大物博"
          ]
        },
        "highlight": {
          "content": [
            "<tag1>中华</tag1>人民共和国"
          ]
        }
      }
    ]
  }
}
```


