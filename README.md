# spring-cloud-function-example


## 概要

関ジャバ'19 7月度 で発表した資料の参考ソースコードです。

資料はこちら。<br>
[サーバーレスをJavaで組むためのSpring Cloud Function入門](https://speakerdeck.com/phonypianist/introduction-to-spring-cloud-function-to-build-serverless-in-java)


## function-example

サーバーレスでSpring Cloud Functionを使用するためのサンプル。<br>
Serverless Frameworkでデプロイして、そのまま試せます。

例として登録している関数は次の通りです。

|関数|説明|
|----|----|
|EchoFunction|messageをそのまま返す関数|
|EchoFunction|EchoFunctionのFlux定義版|
|DispatchFunction|アクセスするとEchoのAPIをそのまま呼び出す関数|
|DispatchFluxFunction|DispatchFunctionのFlux定義版|
|CountUpFunction|アクセスすると、指定したnameをキーにしたDynamoDBの値が1増える関数|
|CountUpFluxFunction|CountUpFunctionのFlux定義版|

## compiler-example

spring-cloud-funciton-compilerとspring-cloud-function-taskを用いて、 `application.yml` に関数とタスクフローを定義したサンプル。
