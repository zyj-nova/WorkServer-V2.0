(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d22cc39"],{f52f:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticStyle:{border:"1px",width:"100%",height:"100%","box-shadow":"0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)"}},[a("el-container",[a("el-header",[a("h1",[e._v("所有任务完成情况")])]),a("el-main",[a("el-table",{attrs:{data:e.completeResuls}},[a("el-table-column",{attrs:{label:"任务名称",prop:"taskname"}}),a("el-table-column",{attrs:{label:"学工号",prop:"uno"}}),a("el-table-column",{attrs:{label:"姓名",prop:"username"}}),a("el-table-column",{attrs:{label:"完成情况",prop:"result"}})],1)],1)],1)],1)},n=[],r=a("a27b"),s={data:function(){return{completeResuls:[]}},created:function(){var e=this;r["a"].get("/taskdetail/get/result").then(function(t){e.completeResuls=t.data.results})}},o=s,u=a("2877"),c=Object(u["a"])(o,l,n,!1,null,"91f7b252",null);t["default"]=c.exports}}]);