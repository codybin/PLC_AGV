/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.strategies.basic.dispatching;

import org.opentcs.components.Lifecycle;

/**
 * Describes a reusable dispatching (sub-)task with a life cycle.
 *描述一个拥有生命周期的可重用的任务
 * @author Stefan Walter (Fraunhofer IML)
 */
public interface Phase
    extends Runnable,
            Lifecycle {

}
