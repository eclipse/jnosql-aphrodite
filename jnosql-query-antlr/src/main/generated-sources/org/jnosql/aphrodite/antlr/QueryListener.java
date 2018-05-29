// Generated from org/jnosql/aphrodite/antlr/Query.g4 by ANTLR 4.7.1
package org.jnosql.aphrodite.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QueryParser}.
 */
public interface QueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QueryParser#select}.
	 * @param ctx the parse tree
	 */
	void enterSelect(QueryParser.SelectContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#select}.
	 * @param ctx the parse tree
	 */
	void exitSelect(QueryParser.SelectContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#delete}.
	 * @param ctx the parse tree
	 */
	void enterDelete(QueryParser.DeleteContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#delete}.
	 * @param ctx the parse tree
	 */
	void exitDelete(QueryParser.DeleteContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#insert}.
	 * @param ctx the parse tree
	 */
	void enterInsert(QueryParser.InsertContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#insert}.
	 * @param ctx the parse tree
	 */
	void exitInsert(QueryParser.InsertContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#update}.
	 * @param ctx the parse tree
	 */
	void enterUpdate(QueryParser.UpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#update}.
	 * @param ctx the parse tree
	 */
	void exitUpdate(QueryParser.UpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#get}.
	 * @param ctx the parse tree
	 */
	void enterGet(QueryParser.GetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#get}.
	 * @param ctx the parse tree
	 */
	void exitGet(QueryParser.GetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#del}.
	 * @param ctx the parse tree
	 */
	void enterDel(QueryParser.DelContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#del}.
	 * @param ctx the parse tree
	 */
	void exitDel(QueryParser.DelContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#put}.
	 * @param ctx the parse tree
	 */
	void enterPut(QueryParser.PutContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#put}.
	 * @param ctx the parse tree
	 */
	void exitPut(QueryParser.PutContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#fields}.
	 * @param ctx the parse tree
	 */
	void enterFields(QueryParser.FieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#fields}.
	 * @param ctx the parse tree
	 */
	void exitFields(QueryParser.FieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#deleteFields}.
	 * @param ctx the parse tree
	 */
	void enterDeleteFields(QueryParser.DeleteFieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#deleteFields}.
	 * @param ctx the parse tree
	 */
	void exitDeleteFields(QueryParser.DeleteFieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#star}.
	 * @param ctx the parse tree
	 */
	void enterStar(QueryParser.StarContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#star}.
	 * @param ctx the parse tree
	 */
	void exitStar(QueryParser.StarContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#skip}.
	 * @param ctx the parse tree
	 */
	void enterSkip(QueryParser.SkipContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#skip}.
	 * @param ctx the parse tree
	 */
	void exitSkip(QueryParser.SkipContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#limit}.
	 * @param ctx the parse tree
	 */
	void enterLimit(QueryParser.LimitContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#limit}.
	 * @param ctx the parse tree
	 */
	void exitLimit(QueryParser.LimitContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#order}.
	 * @param ctx the parse tree
	 */
	void enterOrder(QueryParser.OrderContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#order}.
	 * @param ctx the parse tree
	 */
	void exitOrder(QueryParser.OrderContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#orderName}.
	 * @param ctx the parse tree
	 */
	void enterOrderName(QueryParser.OrderNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#orderName}.
	 * @param ctx the parse tree
	 */
	void exitOrderName(QueryParser.OrderNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#where}.
	 * @param ctx the parse tree
	 */
	void enterWhere(QueryParser.WhereContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#where}.
	 * @param ctx the parse tree
	 */
	void exitWhere(QueryParser.WhereContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(QueryParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(QueryParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#eq}.
	 * @param ctx the parse tree
	 */
	void enterEq(QueryParser.EqContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#eq}.
	 * @param ctx the parse tree
	 */
	void exitEq(QueryParser.EqContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#gt}.
	 * @param ctx the parse tree
	 */
	void enterGt(QueryParser.GtContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#gt}.
	 * @param ctx the parse tree
	 */
	void exitGt(QueryParser.GtContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#gte}.
	 * @param ctx the parse tree
	 */
	void enterGte(QueryParser.GteContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#gte}.
	 * @param ctx the parse tree
	 */
	void exitGte(QueryParser.GteContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#lt}.
	 * @param ctx the parse tree
	 */
	void enterLt(QueryParser.LtContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#lt}.
	 * @param ctx the parse tree
	 */
	void exitLt(QueryParser.LtContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#lte}.
	 * @param ctx the parse tree
	 */
	void enterLte(QueryParser.LteContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#lte}.
	 * @param ctx the parse tree
	 */
	void exitLte(QueryParser.LteContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#between}.
	 * @param ctx the parse tree
	 */
	void enterBetween(QueryParser.BetweenContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#between}.
	 * @param ctx the parse tree
	 */
	void exitBetween(QueryParser.BetweenContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(QueryParser.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(QueryParser.InContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#like}.
	 * @param ctx the parse tree
	 */
	void enterLike(QueryParser.LikeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#like}.
	 * @param ctx the parse tree
	 */
	void exitLike(QueryParser.LikeContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#not}.
	 * @param ctx the parse tree
	 */
	void enterNot(QueryParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#not}.
	 * @param ctx the parse tree
	 */
	void exitNot(QueryParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#asc}.
	 * @param ctx the parse tree
	 */
	void enterAsc(QueryParser.AscContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#asc}.
	 * @param ctx the parse tree
	 */
	void exitAsc(QueryParser.AscContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#desc}.
	 * @param ctx the parse tree
	 */
	void enterDesc(QueryParser.DescContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#desc}.
	 * @param ctx the parse tree
	 */
	void exitDesc(QueryParser.DescContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#and}.
	 * @param ctx the parse tree
	 */
	void enterAnd(QueryParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#and}.
	 * @param ctx the parse tree
	 */
	void exitAnd(QueryParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#or}.
	 * @param ctx the parse tree
	 */
	void enterOr(QueryParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#or}.
	 * @param ctx the parse tree
	 */
	void exitOr(QueryParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#ttl}.
	 * @param ctx the parse tree
	 */
	void enterTtl(QueryParser.TtlContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#ttl}.
	 * @param ctx the parse tree
	 */
	void exitTtl(QueryParser.TtlContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnit(QueryParser.UnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnit(QueryParser.UnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#changes}.
	 * @param ctx the parse tree
	 */
	void enterChanges(QueryParser.ChangesContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#changes}.
	 * @param ctx the parse tree
	 */
	void exitChanges(QueryParser.ChangesContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#change}.
	 * @param ctx the parse tree
	 */
	void enterChange(QueryParser.ChangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#change}.
	 * @param ctx the parse tree
	 */
	void exitChange(QueryParser.ChangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(QueryParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(QueryParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#keys}.
	 * @param ctx the parse tree
	 */
	void enterKeys(QueryParser.KeysContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#keys}.
	 * @param ctx the parse tree
	 */
	void exitKeys(QueryParser.KeysContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(QueryParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(QueryParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(QueryParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(QueryParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#entity}.
	 * @param ctx the parse tree
	 */
	void enterEntity(QueryParser.EntityContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#entity}.
	 * @param ctx the parse tree
	 */
	void exitEntity(QueryParser.EntityContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(QueryParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(QueryParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(QueryParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(QueryParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#convert}.
	 * @param ctx the parse tree
	 */
	void enterConvert(QueryParser.ConvertContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#convert}.
	 * @param ctx the parse tree
	 */
	void exitConvert(QueryParser.ConvertContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(QueryParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(QueryParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(QueryParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(QueryParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(QueryParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(QueryParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#json}.
	 * @param ctx the parse tree
	 */
	void enterJson(QueryParser.JsonContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#json}.
	 * @param ctx the parse tree
	 */
	void exitJson(QueryParser.JsonContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#object_json}.
	 * @param ctx the parse tree
	 */
	void enterObject_json(QueryParser.Object_jsonContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#object_json}.
	 * @param ctx the parse tree
	 */
	void exitObject_json(QueryParser.Object_jsonContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#pair_json}.
	 * @param ctx the parse tree
	 */
	void enterPair_json(QueryParser.Pair_jsonContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#pair_json}.
	 * @param ctx the parse tree
	 */
	void exitPair_json(QueryParser.Pair_jsonContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#value_json}.
	 * @param ctx the parse tree
	 */
	void enterValue_json(QueryParser.Value_jsonContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#value_json}.
	 * @param ctx the parse tree
	 */
	void exitValue_json(QueryParser.Value_jsonContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#array_json}.
	 * @param ctx the parse tree
	 */
	void enterArray_json(QueryParser.Array_jsonContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#array_json}.
	 * @param ctx the parse tree
	 */
	void exitArray_json(QueryParser.Array_jsonContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(QueryParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(QueryParser.ParameterContext ctx);
}