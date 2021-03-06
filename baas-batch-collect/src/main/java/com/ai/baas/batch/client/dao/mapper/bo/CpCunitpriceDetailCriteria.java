package com.ai.baas.batch.client.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class CpCunitpriceDetailCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public CpCunitpriceDetailCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeIsNull() {
            addCriterion("CUNIT_PRICE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeIsNotNull() {
            addCriterion("CUNIT_PRICE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeEqualTo(String value) {
            addCriterion("CUNIT_PRICE_CODE =", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeNotEqualTo(String value) {
            addCriterion("CUNIT_PRICE_CODE <>", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeGreaterThan(String value) {
            addCriterion("CUNIT_PRICE_CODE >", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CUNIT_PRICE_CODE >=", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeLessThan(String value) {
            addCriterion("CUNIT_PRICE_CODE <", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeLessThanOrEqualTo(String value) {
            addCriterion("CUNIT_PRICE_CODE <=", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeLike(String value) {
            addCriterion("CUNIT_PRICE_CODE like", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeNotLike(String value) {
            addCriterion("CUNIT_PRICE_CODE not like", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeIn(List<String> values) {
            addCriterion("CUNIT_PRICE_CODE in", values, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeNotIn(List<String> values) {
            addCriterion("CUNIT_PRICE_CODE not in", values, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeBetween(String value1, String value2) {
            addCriterion("CUNIT_PRICE_CODE between", value1, value2, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeNotBetween(String value1, String value2) {
            addCriterion("CUNIT_PRICE_CODE not between", value1, value2, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeIsNull() {
            addCriterion("PRICE_PRODUCT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeIsNotNull() {
            addCriterion("PRICE_PRODUCT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeEqualTo(String value) {
            addCriterion("PRICE_PRODUCT_TYPE =", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeNotEqualTo(String value) {
            addCriterion("PRICE_PRODUCT_TYPE <>", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeGreaterThan(String value) {
            addCriterion("PRICE_PRODUCT_TYPE >", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PRICE_PRODUCT_TYPE >=", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeLessThan(String value) {
            addCriterion("PRICE_PRODUCT_TYPE <", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeLessThanOrEqualTo(String value) {
            addCriterion("PRICE_PRODUCT_TYPE <=", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeLike(String value) {
            addCriterion("PRICE_PRODUCT_TYPE like", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeNotLike(String value) {
            addCriterion("PRICE_PRODUCT_TYPE not like", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeIn(List<String> values) {
            addCriterion("PRICE_PRODUCT_TYPE in", values, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeNotIn(List<String> values) {
            addCriterion("PRICE_PRODUCT_TYPE not in", values, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeBetween(String value1, String value2) {
            addCriterion("PRICE_PRODUCT_TYPE between", value1, value2, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeNotBetween(String value1, String value2) {
            addCriterion("PRICE_PRODUCT_TYPE not between", value1, value2, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andFactorNameIsNull() {
            addCriterion("FACTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFactorNameIsNotNull() {
            addCriterion("FACTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFactorNameEqualTo(String value) {
            addCriterion("FACTOR_NAME =", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameNotEqualTo(String value) {
            addCriterion("FACTOR_NAME <>", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameGreaterThan(String value) {
            addCriterion("FACTOR_NAME >", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameGreaterThanOrEqualTo(String value) {
            addCriterion("FACTOR_NAME >=", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameLessThan(String value) {
            addCriterion("FACTOR_NAME <", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameLessThanOrEqualTo(String value) {
            addCriterion("FACTOR_NAME <=", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameLike(String value) {
            addCriterion("FACTOR_NAME like", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameNotLike(String value) {
            addCriterion("FACTOR_NAME not like", value, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameIn(List<String> values) {
            addCriterion("FACTOR_NAME in", values, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameNotIn(List<String> values) {
            addCriterion("FACTOR_NAME not in", values, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameBetween(String value1, String value2) {
            addCriterion("FACTOR_NAME between", value1, value2, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorNameNotBetween(String value1, String value2) {
            addCriterion("FACTOR_NAME not between", value1, value2, "factorName");
            return (Criteria) this;
        }

        public Criteria andFactorValueIsNull() {
            addCriterion("FACTOR_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andFactorValueIsNotNull() {
            addCriterion("FACTOR_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andFactorValueEqualTo(String value) {
            addCriterion("FACTOR_VALUE =", value, "factorValue");
            return (Criteria) this;
        }

        public Criteria andFactorValueNotEqualTo(String value) {
            addCriterion("FACTOR_VALUE <>", value, "factorValue");
            return (Criteria) this;
        }

        public Criteria andFactorValueGreaterThan(String value) {
            addCriterion("FACTOR_VALUE >", value, "factorValue");
            return (Criteria) this;
        }

        public Criteria andFactorValueGreaterThanOrEqualTo(String value) {
            addCriterion("FACTOR_VALUE >=", value, "factorValue");
            return (Criteria) this;
        }

        public Criteria andFactorValueLessThan(String value) {
            addCriterion("FACTOR_VALUE <", value, "factorValue");
            return (Criteria) this;
        }

        public Criteria andFactorValueLessThanOrEqualTo(String value) {
            addCriterion("FACTOR_VALUE <=", value, "factorValue");
            return (Criteria) this;
        }

        public Criteria andFactorValueLike(String value) {
            addCriterion("FACTOR_VALUE like", value, "factorValue");
            return (Criteria) this;
        }

        public Criteria andFactorValueNotLike(String value) {
            addCriterion("FACTOR_VALUE not like", value, "factorValue");
            return (Criteria) this;
        }

        public Criteria andFactorValueIn(List<String> values) {
            addCriterion("FACTOR_VALUE in", values, "factorValue");
            return (Criteria) this;
        }

        public Criteria andFactorValueNotIn(List<String> values) {
            addCriterion("FACTOR_VALUE not in", values, "factorValue");
            return (Criteria) this;
        }

        public Criteria andFactorValueBetween(String value1, String value2) {
            addCriterion("FACTOR_VALUE between", value1, value2, "factorValue");
            return (Criteria) this;
        }

        public Criteria andFactorValueNotBetween(String value1, String value2) {
            addCriterion("FACTOR_VALUE not between", value1, value2, "factorValue");
            return (Criteria) this;
        }

        public Criteria andFactorAmountIsNull() {
            addCriterion("FACTOR_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andFactorAmountIsNotNull() {
            addCriterion("FACTOR_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andFactorAmountEqualTo(Long value) {
            addCriterion("FACTOR_AMOUNT =", value, "factorAmount");
            return (Criteria) this;
        }

        public Criteria andFactorAmountNotEqualTo(Long value) {
            addCriterion("FACTOR_AMOUNT <>", value, "factorAmount");
            return (Criteria) this;
        }

        public Criteria andFactorAmountGreaterThan(Long value) {
            addCriterion("FACTOR_AMOUNT >", value, "factorAmount");
            return (Criteria) this;
        }

        public Criteria andFactorAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("FACTOR_AMOUNT >=", value, "factorAmount");
            return (Criteria) this;
        }

        public Criteria andFactorAmountLessThan(Long value) {
            addCriterion("FACTOR_AMOUNT <", value, "factorAmount");
            return (Criteria) this;
        }

        public Criteria andFactorAmountLessThanOrEqualTo(Long value) {
            addCriterion("FACTOR_AMOUNT <=", value, "factorAmount");
            return (Criteria) this;
        }

        public Criteria andFactorAmountIn(List<Long> values) {
            addCriterion("FACTOR_AMOUNT in", values, "factorAmount");
            return (Criteria) this;
        }

        public Criteria andFactorAmountNotIn(List<Long> values) {
            addCriterion("FACTOR_AMOUNT not in", values, "factorAmount");
            return (Criteria) this;
        }

        public Criteria andFactorAmountBetween(Long value1, Long value2) {
            addCriterion("FACTOR_AMOUNT between", value1, value2, "factorAmount");
            return (Criteria) this;
        }

        public Criteria andFactorAmountNotBetween(Long value1, Long value2) {
            addCriterion("FACTOR_AMOUNT not between", value1, value2, "factorAmount");
            return (Criteria) this;
        }

        public Criteria andFactorUnitIsNull() {
            addCriterion("FACTOR_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andFactorUnitIsNotNull() {
            addCriterion("FACTOR_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andFactorUnitEqualTo(String value) {
            addCriterion("FACTOR_UNIT =", value, "factorUnit");
            return (Criteria) this;
        }

        public Criteria andFactorUnitNotEqualTo(String value) {
            addCriterion("FACTOR_UNIT <>", value, "factorUnit");
            return (Criteria) this;
        }

        public Criteria andFactorUnitGreaterThan(String value) {
            addCriterion("FACTOR_UNIT >", value, "factorUnit");
            return (Criteria) this;
        }

        public Criteria andFactorUnitGreaterThanOrEqualTo(String value) {
            addCriterion("FACTOR_UNIT >=", value, "factorUnit");
            return (Criteria) this;
        }

        public Criteria andFactorUnitLessThan(String value) {
            addCriterion("FACTOR_UNIT <", value, "factorUnit");
            return (Criteria) this;
        }

        public Criteria andFactorUnitLessThanOrEqualTo(String value) {
            addCriterion("FACTOR_UNIT <=", value, "factorUnit");
            return (Criteria) this;
        }

        public Criteria andFactorUnitLike(String value) {
            addCriterion("FACTOR_UNIT like", value, "factorUnit");
            return (Criteria) this;
        }

        public Criteria andFactorUnitNotLike(String value) {
            addCriterion("FACTOR_UNIT not like", value, "factorUnit");
            return (Criteria) this;
        }

        public Criteria andFactorUnitIn(List<String> values) {
            addCriterion("FACTOR_UNIT in", values, "factorUnit");
            return (Criteria) this;
        }

        public Criteria andFactorUnitNotIn(List<String> values) {
            addCriterion("FACTOR_UNIT not in", values, "factorUnit");
            return (Criteria) this;
        }

        public Criteria andFactorUnitBetween(String value1, String value2) {
            addCriterion("FACTOR_UNIT between", value1, value2, "factorUnit");
            return (Criteria) this;
        }

        public Criteria andFactorUnitNotBetween(String value1, String value2) {
            addCriterion("FACTOR_UNIT not between", value1, value2, "factorUnit");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}