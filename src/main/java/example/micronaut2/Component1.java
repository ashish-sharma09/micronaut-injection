package example.micronaut2;

import javax.inject.Singleton;

@Singleton
public class Component1 implements Component {
    @Override
    public String getName() {
        return "Component1";
    }

    @Override
    public Status getStatus() {
        return Status.OK;
    }
}
