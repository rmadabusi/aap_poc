'use strict';

application
  .service('PropertyService', ['$http', '$q', function TriggerService($http, $q) {
    return {
      /**
       * This will return the API Url
       * @param apiUrlName - Name of the required API
       * @returns {Arrya | Object}
       */
      getAPIUrls: function(apiUrlName) {
        var siteAPIUrls = {
          'petsearchFamily': 'pets/families/',
          'petsearchColor': 'pets/colors/',
          'siteUsagemetrics': 'pets/usage_metrics',
          'userIpDetail': 'getUserIp'
        };

        var apiUrlDetails = (angular.isDefined(siteAPIUrls[apiUrlName])) ? siteAPIUrls[apiUrlName] : {};
        return apiUrlDetails;
      },

      /**
       * This will return the Clans Details
       * @returns {Arrya | Object}
       */
      getClansDetails: function() {
        var clans = {
          '1': {'clan_name' : 'dog', 'clan_id' : '1', 'family_id' : '1187,1165,1166,1167,187,1,2,800,3,4,361,801,7,8,9,10,802,12,13,14,15,803,189,27,17,18,19,804,20,193,21,194,22,23,25,26,28,30,31,381,805,33,34,35,36,38,196,39,40,41,230,521,44,45,199,46,621,242,47,29,49,641,51,52,808,201,810,811,54,202,812,813,56,806,203,814,58,204,815,60,61,62,63,205,64,661,206,501,816,817,281,67,207,208,68,69,818,70,71,72,819,820,73,209,821,74,822,211,826,827,76,77,78,828,200,80,829,83,214,831,85,215,832,87,88,833,89,90,581,834,92,93,84,213,94,226,96,835,218,664,98,99,100,101,102,662,103,836,837,104,219,105,220,107,108,110,111,113,221,841,222,846,561,224,225,118,848,119,120,121,849,227,123,124,125,212,244,42,195,310,1368,5,601,210,216,663,198,838,840,842,843,844,845,847,1170,830,57,1169,16,95,302,461,37,127,55,441,192,191,24,823,1186,1188,1189,1168,1369,1082', 'color_id' : '59,45,46,47,49,51,54,55,56,62,63,64,50,60,61,66,67,68,74,75,80,83,84,85,92,93,95,96,97'},
          '2': {'clan_name' : 'cat', 'clan_id' : '2', 'family_id' : '977,967,968,969,970,971,972,973,974,976,979,980,981,982,983,984,985,986,1119,1120,1122,1123,1124,1125,1126,1127,1128,1129,1130,1131,1132,1133,1134,1135,1136,1137,1138,1139,1140,1141,1142,1143,1146,1147,1148,1149,1151,1145,1190,1191,1192', 'color_id' : '1,100,101,102,2,4,5,6,12,7,9,13,10,11,14,21,15,22,23,44,104,8,20'},
          '3': {'clan_name' : 'rabbit', 'clan_id' : '3', 'family_id' : '1199,1201,1202,1203,1204,1205,1206,1207,1208,1209,1210,1211,1212,1213,1216,1217,1218,1223,1224,1225,1227,1228,1229,1231,1232,1233,1234,1235,1236,1237,1238,1239,1241,1242,1243,1245,1246,1230,1214,1219,1221,1240,1200,1222,1244,1215,1220,1226,1364,1365,1366', 'color_id' : '105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128'},
          '4': {'clan_name' : 'small', 'clan_id' : '4', 'family_id' : '1247,1248,1249,1250,1251,1252,1253,1254,1255,1256,1257,1258', 'color_id' : '129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146'},
          '5': {'clan_name' : 'bird', 'clan_id' : '5', 'family_id' : '1259,1260,1261,1262,1263,1264,1265,1266,1267,1268,1269,1270,1271,1272,1273,1274,1275,1276,1277,1278,1279,1280,1281,1282,1283,1286,1287,1288,1289,1290,1291,1292,1293,1294,1295,1297,1298,1299,1367,1284,1285,1296', 'color_id' : '147,148,149,150,151,152,153,154,155,156,157,158,159,160,161'},
          '6': {'clan_name' : 'horse', 'clan_id' : '6', 'family_id' : '1307,1370,1300,1301,1302,1303,1304,1305,1306,1308,1309,1310,1311,1312,1313,1314,1315,1316,1317,1318,1319,1321,1322,1323,1324,1325,1326,1327,1328,1329,1330,1331,1332,1333,1334,1335,1336,1337,1338,1339,1340,1341,1342,1320', 'color_id' : '162,163,164,165,166,167,168,169,170,171,172,173,174,175,176'},
          '7': {'clan_name' : 'reptile, amphibian, and/or fish', 'clan_id' : '7', 'family_id' : '1343,1344,1345,1346,1349,1350,1351,1352,1353,1354,1355,1347,1348', 'color_id' : ''},
          '8': {'clan_name' : 'farm-type', 'clan_id' : '8', 'family_id' : '1356,1358,1359,1360,1361,1362,1363,1357', 'color_id' : ''}
        };
        return clans;
      },

      /**
       * This will return the Search Tab Details
       * @returns {Arrya | Object}
       */
      getSearchTabs: function() {
        var searchTabs = {
          '1': {'name' : 'dog', 'label' : 'Dogs', 'isActive' : true, 'clan_id' : '1'},
          '2': {'name' : 'cat', 'label' : 'Cats', 'isActive' : false, 'clan_id' : '2'},
          '3': {'name' : 'others', 'label' : 'Others', 'isActive' : false, 'clan_id' : 'others'},
        };
        return searchTabs;
      },

      /**
       * This will return the Pet Type Details
       * @returns {Arrya | Object}
       */
      getPetTypeDetails: function() {
        var petTypeDetails = {
          '1': {'clan_name' : 'Birds', 'clan_id' : '5'},
          '2': {'clan_name' : 'Farm-Type Animals', 'clan_id' : '8'},
          '3': {'clan_name' : 'Horses', 'clan_id' : '6'},
          '4': {'clan_name' : 'Rabbits', 'clan_id' : '3'},
          '5': {'clan_name' : 'Reptiles, Amphibians, & Fish', 'clan_id' : '7'},
          '6': {'clan_name' : 'Small Animals', 'clan_id' : '4'}
        };
        return petTypeDetails;
      }

    };
  }]);
