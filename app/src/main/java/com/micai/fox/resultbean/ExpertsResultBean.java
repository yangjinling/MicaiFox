package com.micai.fox.resultbean;

import java.util.List;

/**
 * Created by lq on 2018/2/5.
 */

public class ExpertsResultBean {

    /**
     * execResult : true
     * execDatas : {"length":20,"pageNo":1,"totalRow":2,"from":0,"recordList":[{"proId":"100001","proName":"aaa","proPhoto":"/mcfox/userfiles/5edb348b25a248e185a6f12c9204fa561516523589992/images/photo/2018/02/75e15cac9e69538a72bc53832cdb00d1.jpg","proAuth":"我是认证"},{"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang"}],"needCount":true,"totalPage":1}
     * count : 0
     * num : 0
     */

    private boolean execResult;
    private ExecDatasBean execDatas;
    private int count;
    private int num;

    public boolean isExecResult() {
        return execResult;
    }

    public void setExecResult(boolean execResult) {
        this.execResult = execResult;
    }

    public ExecDatasBean getExecDatas() {
        return execDatas;
    }

    public void setExecDatas(ExecDatasBean execDatas) {
        this.execDatas = execDatas;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static class ExecDatasBean {
        /**
         * length : 20
         * pageNo : 1
         * totalRow : 2
         * from : 0
         * recordList : [{"proId":"100001","proName":"aaa","proPhoto":"/mcfox/userfiles/5edb348b25a248e185a6f12c9204fa561516523589992/images/photo/2018/02/75e15cac9e69538a72bc53832cdb00d1.jpg","proAuth":"我是认证"},{"proId":"5edb348b25a248e185a6f12c9204fa561516523589992","proName":"zhang","proPhoto":"/admin-mcfox/userfiles/1/images/photo/2018/01/75e15cac9e69538a72bc53832cdb00d1(1).jpg","proAuth":"zhang"}]
         * needCount : true
         * totalPage : 1
         */

        private int length;
        private int pageNo;
        private int totalRow;
        private int from;
        private boolean needCount;
        private int totalPage;
        private List<RecordListBean> recordList;

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getTotalRow() {
            return totalRow;
        }

        public void setTotalRow(int totalRow) {
            this.totalRow = totalRow;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public boolean isNeedCount() {
            return needCount;
        }

        public void setNeedCount(boolean needCount) {
            this.needCount = needCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<RecordListBean> getRecordList() {
            return recordList;
        }

        public void setRecordList(List<RecordListBean> recordList) {
            this.recordList = recordList;
        }

        public static class RecordListBean {
            /**
             * proId : 100001
             * proName : aaa
             * proPhoto : /mcfox/userfiles/5edb348b25a248e185a6f12c9204fa561516523589992/images/photo/2018/02/75e15cac9e69538a72bc53832cdb00d1.jpg
             * proAuth : 我是认证
             */

            private String proId;
            private String proName;
            private String proPhoto;
            private String proAuth;

            public String getProId() {
                return proId;
            }

            public void setProId(String proId) {
                this.proId = proId;
            }

            public String getProName() {
                return proName;
            }

            public void setProName(String proName) {
                this.proName = proName;
            }

            public String getProPhoto() {
                return proPhoto;
            }

            public void setProPhoto(String proPhoto) {
                this.proPhoto = proPhoto;
            }

            public String getProAuth() {
                return proAuth;
            }

            public void setProAuth(String proAuth) {
                this.proAuth = proAuth;
            }
        }
    }
}
