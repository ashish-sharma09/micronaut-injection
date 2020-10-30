package example.micronaut2;

import javax.inject.Singleton;

@Singleton
public class Component2 implements Component {

    @Override
    public String getName() {
        return "Component2";
    }

    @Override
    public Status getStatus() {
        return Status.OK;
    }
}
