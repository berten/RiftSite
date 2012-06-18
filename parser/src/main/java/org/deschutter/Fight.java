
package org.deschutter;

import java.util.ArrayList;
import java.util.List;
import org.deschutter.parser.actions.RiftAction;

/**
 *
 * @author berten
 */
public class Fight {

        private List<RiftAction> actions = new ArrayList<>();

        public void addAction(RiftAction action) {
            actions.add(action);
        }

        public List<RiftAction> getActions() {
            return actions;
        }
    }
