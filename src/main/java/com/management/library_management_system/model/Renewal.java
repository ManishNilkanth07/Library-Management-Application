package com.management.library_management_system.model;

import java.util.Date;

public class Renewal {

    private int renewalId;

    private int issueId;

    private Date renewalDate;

    public Renewal(RenewalBuilder builder) {

        this.renewalId = builder.renewalId;

        this.issueId = builder.issueId;

        this.renewalDate = builder.renewalDate;
    }

    public int getRenewalId() {
        return renewalId;
    }

    public int getIssueId() {
        return issueId;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    @Override
    public String toString() {
        return "Renewal{" + "renewalId=" + renewalId + ", issueId=" + issueId + ", renewalDate=" + renewalDate + '}';
    }

    public static class RenewalBuilder {

        private int renewalId;

        private int issueId;

        private Date renewalDate;

        public RenewalBuilder setRenewalId(int renewalId) {
            this.renewalId = renewalId;
            return this;
        }

        public RenewalBuilder setIssueId(int issueId) {
            this.issueId = issueId;
            return this;
        }

        public RenewalBuilder setRenewalDate(Date renewalDate) {
            this.renewalDate = renewalDate;
            return this;
        }

        public Renewal build() {
            return new Renewal(this);
        }

    }

}
