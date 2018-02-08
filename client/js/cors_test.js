(function () {
  var CorsTestConfig = {};

  function config(config) {
    if (typeof config === 'object') {
      CorsTestConfig.serverUrl = config.serverUrl;
    }
  }

  function testGet(echoElement) {
    $Ajax.Get(CorsTestConfig.serverUrl + "/test", function (xmlHttp) {
      echoElement.innerHTML = xmlHttp.responseText;
    });
  }

  window.CorsTest = {
    config: config,
    testGet: testGet
  }
})();


