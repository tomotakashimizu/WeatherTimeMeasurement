# WeatherTimeMeasurement

WeatherTimeMeasurement は OpenWeatherMap API を用いて計測したい天気の時間を記録することができます。それらの記録は Elasticsearch, PostgreSQL に保存されます。また Elasticsearch に保存されたデータは Kibana で可視化することでわかりやすく表示できます。

## 利用サービス

### Elasticsearch

##### 起動

`$ elasticsearch`

##### 起動の確認

`$ curl http://localhost:9200/`

### Kibana

##### 起動

`$ kibana`
