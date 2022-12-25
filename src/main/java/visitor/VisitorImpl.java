package visitor;

import java.util.Map;

public class VisitorImpl implements Visitor {

    private final String groupId;

    public VisitorImpl(String id) {
        this.groupId = id;
    }

    @Override
    public <T> Map<String, String> onSignature(Signature<T> signature) {
        return Map.of("groups", groupId);
    }

    @Override
    public <T> Map<String, String> onGroup(Group<T> group) {
        return Map.of("groups", groupId);
    }
}
