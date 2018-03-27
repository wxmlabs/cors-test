(function () {
  var CorsTestConfig = {};
  var testUrl;

  function config(config) {
    if (typeof config === 'object') {
      CorsTestConfig.serverUrl = config.serverUrl;
      CorsTestConfig.apiId = config.apiId;
      testUrl = CorsTestConfig.serverUrl + "/" + CorsTestConfig.apiId + "/captcha";
    }
  }

  function testPost(echoElement) {
    var jsonObj = {
      test: true,
      time: new Date()
    };
    $Ajax.Post(testUrl, jsonObj, function (xmlHttp) {
      echoElement.innerHTML = xmlHttp.responseText;
    })
  }

  window.CorsTest = {
    config: config,
    testPost: testPost
  }
})();


