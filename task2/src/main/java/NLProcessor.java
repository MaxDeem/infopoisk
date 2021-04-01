import lombok.*;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.InputStream;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NLProcessor {
    private InputStream stream;
    private SentenceModel model;
    private SentenceDetectorME detector;
    private String[] sentences;

    @SneakyThrows
    public NLProcessor(String modelSource) {
        this.stream = getClass().getResourceAsStream("src/main/resources/en-sent.bin");
        this.model = new SentenceModel(stream);
        this.detector = new SentenceDetectorME(model);
    }

    public void detectSentences(String text) {
        sentences = detector.sentDetect(text);
    }
}
