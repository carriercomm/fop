/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* $Id$ */

package org.apache.fop.area;

import java.util.List;

import org.apache.fop.fo.extensions.destination.Destination;
import org.apache.fop.area.PageViewport;
/**
 * An instance of this class is named destination from fox:destination
 */
public class DestinationData extends AbstractOffDocumentItem implements Resolvable {

    // PDFReference (object reference) for this destination
    private String goToReference;

    // ID Reference for this bookmark
    private String idRef;

    // String Array to satisfy getIDRefs method
    private String[] idRefs;

    // PageViewport that the idRef item refers to
    private PageViewport pageRef = null;

    /**
     * Create a new pdf destination data object.
     * This is used by the destination to create a data object
     * with a idref.  During processing, this idref will be
     * subsequently resolved to a particular PageViewport.
     *
     * @param destination the fo:bookmark object
     */
    public DestinationData(Destination destination) {
        idRef = destination.getInternalDestination();
        idRefs = new String[] {idRef};
    }

    /**
     * Get the idref for this destination
     *
     * @return the idref for the destination
     */
    public String getIDRef() {
        return idRef;
    }

    /**
     * @see org.apache.fop.area.Resolvable#getIDRefs()
     */
    public String[] getIDRefs() {
        return idRefs;
    }

    /**
     * Get the PageViewport object that this destination refers to
     *
     * @return the PageViewport that this destination points to
     */
    public PageViewport getPageViewport() {
        return pageRef;
    }

    /**
     * Set the GoToReference for this destination
     *
     * @param goToReference the GoToReference to associate with this destination
     */
    public void setGoToReference(String goToReference) {
        this.goToReference = goToReference;
    }

    /**
     * Get the GoToReference for this destination
     *
     * @return the GoToReference associated with this destination
     */
    public String getGoToReference() {
        return goToReference;
    }

    /**
     * Check if this resolvable object has been resolved.
     * For now, just return true.
     * To do: Find a way to determine whether the destination has been resolved.
     *
     * @return true if this object has been resolved
     */
    public boolean isResolved() {
        return true;
    }

    /**
     * Resolves the idref of this object by getting the PageViewport
     * object that corresponds to the IDRef
     *
     * @see org.apache.fop.area.Resolvable#resolveIDRef(String, List)
     * @todo check to make sure it works if multiple bookmark-items
     * have the same idref
     */
    public void resolveIDRef(String id, List pages) {
        pageRef = (PageViewport) pages.get(0);
        // TODO get rect area of id on page
    }

    /**
     * @see org.apache.fop.area.OffDocumentItem#getName()
     */
    public String getName() {
        return "Destination";
    }

}
