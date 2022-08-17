package labshopreqresp.infra;

import labshopreqresp.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class InventoryHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Inventory>> {

    @Override
    public EntityModel<Inventory> process(EntityModel<Inventory> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/decrease-stock")
                .withRel("decrease-stock")
        );

        return model;
    }
}
