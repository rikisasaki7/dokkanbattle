参考WEBページ

・Eclipseが起動しなかったら
⇒iniファイルの-vmに指定しているjava実行ファイルのパスを修正
https://ninnin.in/diary/java-was-started-but-returned-exit-code13/

・workspace
⇒佐々木はD配下を指定しているので、各自修正してください。

・パースペクティブ
⇒右上のJavaとか書いているパースペクティブに「Spring」を追加
⇒readme.xlsx
　■Springパースペクティブ

・仕様技術
Spring boot + h2DB + gradle（gradleとh2dbの接続をするWEBページが多かったため）
※sts／gradleプラグインは以下でインストール
ヘルプ＞Eclipseマーケットプレース
　検索タブで「sts」と検索
　検索タブで「Buildship Gradle」と検索

・Spring Starter Projectの作成とgradleプラグインのインストール
https://runble1.com/spring-gradle-project/
⇒springパースペクティブはreadme.xlsxを参照

・HelloWorld
https://runble1.com/springboot-helloworld/
⇒readme.xlsx
　■Spring Bootアプリの起動

・h2DBと接続
https://qiita.com/yuk1ty/items/6cc4f69ad25cc3eeb59b
　　⇒手順１と３
　　⇒SpringBootの起動はHelloWorldを参照
http://web-dev.hatenablog.com/entry/spring-boot/intro/connect-h2db
　　⇒手順２と３

以下URLにブラウザでアクセス
http://localhost:8080/h2-console
　　⇒入力は以下
　　Driver Class：org.h2.Driver
　　JDBC URL：jdbc:h2:mem:AZ;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE
　　User Name：sa
　　Password
⇒DokkanbattleApplication.javaで実行したcreateとinsertが実行されている。
⇒readme.xlsx
　■Spring Bootアプリの起動・H2DBへの接続

・spring boot + gradleでJSPを使う
http://xblood.hatenablog.com/entry/2016/12/13/223000


・h2DBにアクセスしてDBの情報を画面表示する（まだできていない）
