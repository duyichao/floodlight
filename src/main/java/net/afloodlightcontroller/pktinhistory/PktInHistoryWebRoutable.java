package net.afloodlightcontroller.pktinhistory;

import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import net.floodlightcontroller.restserver.RestletRoutable;

public class PktInHistoryWebRoutable implements RestletRoutable {

	@Override
	public Restlet getRestlet(Context context) {
		// TODO 自动生成的方法存根
		Router router = new Router(context);
        router.attach("/history/json", PktInHistoryResource.class);
        return router;
	}

	@Override
	public String basePath() {
		// TODO 自动生成的方法存根
		return "/wm/pktinhistory";
	}

}
