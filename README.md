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

### PostgreSQL

##### 起動

`$ brew services start postgresql`

##### ユーザアカウントを追加

`$ createuser -P $account_name`

##### データベース作成

`$ createdb $db_name -O $account_name`

##### データベース確認

`$ psql -l`

##### データベースへ接続

```
$ psql -U $account_name $db_name

$db_name=>
```

##### テーブルを作成し、確認

```
$db_name=> CREATE TABLE testtable (id integer, name varchar(100));
$db_name=> insert into testtable(id, name) values(1, 'aa');
$db_name=> select * from testtable;
```

##### 停止

`$ brew services stop postgresql`
