/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
import React from "react";
// import { IdentificationContext } from "../../../../contexts/IdentificationContext";
import { Route, Switch } from "react-router-dom";
import GlossaryAuthorCRUD from "../GlossaryAuthorCRUD";
import GlossaryAuthorNavigation from "../GlossaryAuthorNavigation";
import GlossaryAuthorSearch from "../GlossaryAuthorSearch";
import QuickTerms from "../QuickTerms";
import GlossaryChildren from "../NodeChildren";
import CreateGlossary from "../CreateGlossary";
import UpdateGlossary from "../UpdateGlossary";

export default function GlossaryAuthorRoutes({ glossaryAuthorURL }) {

  console.log("glossaryAuthorURL=" + glossaryAuthorURL);

  function getGlossariesPath() {
    const path = glossaryAuthorURL + "/glossaries";
    console.log("getGlossariesPath " + path);
    return path;
  }
  function getGlossariesAddPath() {
    const path = getGlossariesPath() + "/add-node";
    console.log("getGlossariesAddPath " + path);
    return path;
  }
  function getQuickTermsPath() {
    const path = getGlossariesPath() + "/:guid/quick-terms";
    console.log("getQuickTerms " + path);
    return path;
  }
  function getGlossaryChildrenPath() {
    const path = getGlossariesPath() + "/:guid/children";
    console.log("getGlossaryChildren " + path);
    return path;
  }
  function getGlossariesEditPath() {
    return getGlossariesPath() + "/edit-node/:guid";
  }
  function getCrudPath() {
    const path = glossaryAuthorURL + "/crud";
    console.log("getCrudPath " + path);
    return path;
  }
  function getSearchPath() {
    const path = glossaryAuthorURL + "/search";
    console.log("getSearchPath " + path);
    return path;
  }
  return (
    <Switch>
      <Route
        path={getGlossariesAddPath()}
        component={CreateGlossary}
      ></Route>
      <Route path={getGlossariesEditPath()} component={UpdateGlossary}></Route>
      <Route
        exact
        path={getGlossariesPath()}
        component={GlossaryAuthorNavigation}
      ></Route>
      <Route path={getSearchPath()} component={GlossaryAuthorSearch}></Route>
      <Route path={getQuickTermsPath()} component={QuickTerms}></Route>
      <Route path={getGlossaryChildrenPath()} component={GlossaryChildren}></Route>
      <Route
        path={getGlossariesPath()}
        component={GlossaryAuthorNavigation}
      ></Route>
      <Route
        path={getCrudPath()}
        component={GlossaryAuthorCRUD}
      ></Route>
      <Route
        path={glossaryAuthorURL}
        exact
        component={GlossaryAuthorNavigation}
      ></Route>
      <Route path="/" render={() => <h1>Route not recognised</h1>}></Route>
      {/* <Route render={() => <h1>Route not recognised!!</h1>}></Route> */}
    </Switch>
  );
}
