/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
import React, { createContext, useState } from "react";
import PropTypes from "prop-types";

export const IdentificationContext = createContext();
export const IdentificationContextConsumer = IdentificationContext.Consumer;

const IdentificationContextProvider = props => {

  const [userId, setUserId] = useState("");
  // TODO this will change if the user changes the url - are there any implications to that?
  const [serverName] = useState(window.location.pathname.split("/")[1]);
  const [authenticated, setAuthenticated] = useState(false); //TODO do we need this?

  /**
   * Get the home url for the browser
   */
  const getBrowserURL = pageName => {
    if (process.env.NODE_ENV === "development") {
      // if running in development, looks in .env file to find server name
      const remoteServerName = JSON.parse(process.env[Object.keys(process.env).find(element => element.includes("REACT_APP_EGERIA"))]).remoteServerName;
      return "/" + remoteServerName + "/" + pageName;
    }
    return "/" + serverName + "/" + pageName;
  };
  /**
  * Get the url to use for rest calls 
  * @param {*} serviceName this is the viewservice name. 
  */
  const getRestURL = (serviceName) => {
    const url =
      window.location.protocol +
      "//" +
      window.location.hostname +
      ":" +
      window.location.port +
      "/servers/" +
      serverName +
      "/" +
      serviceName +
      "/users/" +
      userId;
      console.log("rest url is ", url);
    return url;
  };

  return (
    <IdentificationContext.Provider
      value={{
        userId,
        setUserId,
        authenticated,
        setAuthenticated,
        serverName,
        getBrowserURL,
        getRestURL
      }}
    >
      {props.children}
    </IdentificationContext.Provider>
  );

};

IdentificationContextProvider.propTypes = {
  children: PropTypes.node
};

export default IdentificationContextProvider;
// export default IdentificationContext;
