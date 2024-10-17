Build Instructions Kafka Install Kafka
(https://kafka.apache.org/downloads eg kafka_2.12-3.7.0.tgz) Unzip
entirely in folder (you may need to use a third party software to do
this, such as 7-zip https://www.7-zip.org/) Navigate to directory in two
separate CMD/terminal instances Windows: cd "x" Type one into each
Windows: C:\\kafka\\bin\\windows\\zookeeper-server-start.bat
C:\\kafka\\config\\zookeeper.properties
C:\\kafka\\bin\\windows\\kafka-server-start.bat
C:\\kafka\\config\\server.properties Linux and MacOS
./bin/zookeeper-server-start.sh ./config/zookeeper.properties
./bin/kafka-server-start.sh ./config/server.properties

Now your Kafka server is ready to go.

Problems at Runtime Reset TMP Storage (often gets full after repeated
use) rmdir /s /q C:\\tmp\\kakfa-logs rmdir /s /q C:\\tmp\\zookeeper

List all topics (assuming Kafka dir is in C:\\)
C:\\kafka\\bin\\windows\\kafka-topics.bat
--bootstrap-server=localhost:9092 \--list List events in each topic
C:\\kafka\\bin\\windows\\kafka-console-consumer.bat --bootstrap-server
localhost:9092 \--topic bookingevents \--from-beginning
C:\\kafka\\bin\\windows\\kafka-console-consumer.bat --bootstrap-server
localhost:9092 \--topic subscriptionevents \--from-beginning

SpingBoot Download submitted zip file Extract zip Open subfolder in IDE
Wait for contents to index Select each application file, eg
"EventServiceApplication.java" From this file, run the application
(usually top right of IDE) The build outputs tell you what localhost
port the server will be hosted on (8082 in this instance) Open
http://localhost:8082/ (or whatever the port instance is) From here you
can navigate the class created objects by adding /events or /events/1 to
specify an id curl GET/POST/PUT requests can be handled in IDE terminal
(if it allows it, in our case it returned errors there), or CMD (or
other OS terminal) by navigating to the project location. In Windows
this can be done by typing cd "project path".
