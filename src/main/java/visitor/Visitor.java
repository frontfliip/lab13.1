package visitor;

import java.util.Map;

public interface Visitor {
    <T> Map<String, String> onSignature(Signature<T> signature);

    <T> Map<String, String> onGroup(Group<T> group);
}
