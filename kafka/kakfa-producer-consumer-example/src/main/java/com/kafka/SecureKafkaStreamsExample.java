public class KafkaStreamsExample {
    public static void main(final String[] args) {
        final String secureBootstrapServers = args.length > 0 ? args[0] : "localhost:9093";
        final Properties streamsConfiguration = new Properties();
        // Give the Streams application a unique name.  The name must be unique in the Kafka cluster  against which the application is run.
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-app");
        streamsConfiguration.put(StreamsConfig.CLIENT_ID_CONFIG, "kafka-streams-app-client");
        // Where to find secure (!) Kafka broker(s).  In the VM, the broker listens on port 9093 for SSL connections.
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, secureBootstrapServers);
        // Specify default (de)serializers for record keys and for record values.
        streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass().getName());
        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass().getName());
        final StreamsBuilder builder = new StreamsBuilder();
        // Write the input data as-is to the output topic.
        builder.stream("telephone-direcory").to("subdir-fir-surname-shah");
        final KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration);
        // Always (and unconditionally) clean local state prior to starting the processing topology.
        // We opt for this unconditional call here because this will make it easier for you to play around with the example
        // when resetting the application for doing a re-run (via the Application Reset Tool,
        // https://docs.confluent.io/platform/current/streams/developer-guide/app-reset-tool.html).
        // The drawback of cleaning up local state prior is that your app must rebuilt its local state from scratch,
        // which will take time and will require reading all the state-relevant data from the Kafka cluster over the network.
        // Thus in a production scenario you typically do not want to clean up always as we do here but rather only when it is truly needed,
        // i.e., only under certain conditions (e.g., the presence of a command line flag for your app).
        // See `ApplicationResetExample.java` for a production-like example.
        streams.cleanUp();
        streams.start();
        // Add shutdown hook to respond to SIGTERM and gracefully close Kafka Streams
        Runtime.getRuntime().addShutdownHook(new Thread(() -> streams.close()));
    }
}
