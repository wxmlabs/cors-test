(function () {
  var CorsTestConfig = {};
  var testUrl;

  function config(config) {
    if (typeof config === 'object') {
      CorsTestConfig.serverUrl = config.serverUrl;
      testUrl = CorsTestConfig.serverUrl + "/captcha";
    }
  }

  function testGet(echoElement) {
    $Ajax.Get(testUrl, function (xmlHttp) {
      echoElement.innerHTML = xmlHttp.responseText;
    });
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

  function testPut(echoElement) {
    var jsonObj = {
      test: true,
      time: new Date()
    };
    $Ajax.Put(testUrl, jsonObj, function (xmlHttp) {
      echoElement.innerHTML = xmlHttp.responseText;
    })
  }

  function testPatch(echoElement) {
    var jsonObj = {
      test: true,
      time: new Date()
    };
    $Ajax.Patch(testUrl, jsonObj, function (xmlHttp) {
      echoElement.innerHTML = xmlHttp.responseText;
    })
  }

  function testDelete(echoElement) {
    $Ajax.Delete(testUrl, function (xmlHttp) {
      echoElement.innerHTML = xmlHttp.responseText;
    });
  }

  window.CorsTest = {
    config: config,
    testGet: testGet,
    testPost: testPost,
    testPut: testPut,
    testPatch: testPatch,
    testDelete: testDelete
  }
})();


