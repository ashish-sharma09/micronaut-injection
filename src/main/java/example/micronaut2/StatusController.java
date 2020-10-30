package example.micronaut2;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StatusController {

    private final List<Component> components;

    public StatusController(List<Component> components) {
        this.components = components;
    }

    @Get("/_status")
    ApplicationStatus status() {
        return new ApplicationStatus(
            components.stream().sorted(Comparator.comparing(Component::getName)).collect(Collectors.toList()),
            components.stream().allMatch(c -> c.getStatus() == Status.OK) ? Status.OK : Status.FAIL
        );
    }

    public static class ApplicationStatus {
        private final List<Component> components;
        private final Status status;

        private ApplicationStatus(List<Component> components, Status status) {
            this.components = components;
            this.status = status;
        }

        public List<Component> getComponents() {
            return components;
        }

        public Status getStatus() {
            return status;
        }
    }
}
