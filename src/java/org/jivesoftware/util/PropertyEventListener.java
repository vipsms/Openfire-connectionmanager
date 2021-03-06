/**
 * $RCSfile$
 * $Revision: 1705 $
 * $Date: 2005-07-26 14:10:33 -0300 (Tue, 26 Jul 2005) $
 *
 * Copyright (C) 2006 Jive Software. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jivesoftware.util;

import java.util.Map;

/**
 * Interface to listen for property events. Use the
 * {@link org.jivesoftware.util.PropertyEventDispatcher#addListener(PropertyEventListener)}
 * method to register for events.
 *
 * @author Matt Tucker
 */
public interface PropertyEventListener {

    /**
     * A property was set.
     *
     * @param property the property.
     * @param params event parameters.
     */
    public void propertySet(String property, Map<String, Object> params);

    /**
     * A property was deleted.
     *
     * @param property the deleted.
     * @param params event parameters.
     */
    public void propertyDeleted(String property, Map<String, Object> params);

    /**
     * An XML property was set.
     *
     * @param property the property.
     * @param params event parameters.
     */
    public void xmlPropertySet(String property, Map<String, Object> params);

    /**
     * An XML property was deleted.
     *
     * @param property the property.
     * @param params event parameters.
     */
    public void xmlPropertyDeleted(String property, Map<String, Object> params);

}