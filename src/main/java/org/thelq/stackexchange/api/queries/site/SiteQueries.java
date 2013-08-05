/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import com.google.common.base.Preconditions;
import java.util.Collection;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.types.AnswerEntry;
import org.thelq.stackexchange.api.model.types.BadgeEntry;
import org.thelq.stackexchange.api.model.types.CommentEntry;
import org.thelq.stackexchange.api.model.types.EventEntry;
import org.thelq.stackexchange.api.model.types.InboxItemEntry;
import org.thelq.stackexchange.api.model.types.InfoEntry;
import org.thelq.stackexchange.api.model.types.NotificationEntry;
import org.thelq.stackexchange.api.model.types.PostEntry;
import org.thelq.stackexchange.api.model.types.PrivlegeEntry;
import org.thelq.stackexchange.api.model.types.QuestionEntry;
import org.thelq.stackexchange.api.model.types.QuestionTimelineEntry;
import org.thelq.stackexchange.api.model.types.ReputationEntry;
import org.thelq.stackexchange.api.model.types.ReputationHistoryEntry;
import org.thelq.stackexchange.api.model.types.RevisionEntry;
import org.thelq.stackexchange.api.model.types.SuggestedEditEntry;
import org.thelq.stackexchange.api.model.types.TagEntry;
import org.thelq.stackexchange.api.model.types.TagSynonymEntry;
import org.thelq.stackexchange.api.model.types.TagWikiEntry;
import org.thelq.stackexchange.api.model.types.TopTagEntry;
import org.thelq.stackexchange.api.model.types.UserEntry;
import org.thelq.stackexchange.api.model.types.UserTimelineEntry;
import org.thelq.stackexchange.api.model.types.WritePermissionEntry;
import org.thelq.stackexchange.api.queries.QueryUtils;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.BadgeSort;
import org.thelq.stackexchange.api.queries.site.sort.BadgeTypeSort;
import org.thelq.stackexchange.api.queries.site.sort.CommentSort;
import org.thelq.stackexchange.api.queries.site.sort.PostSort;
import org.thelq.stackexchange.api.queries.site.sort.QuestionFavoriteSort;
import org.thelq.stackexchange.api.queries.site.sort.QuestionRelatedSort;
import org.thelq.stackexchange.api.queries.site.sort.QuestionTaggedSort;
import org.thelq.stackexchange.api.queries.site.sort.SuggestedEditSort;
import org.thelq.stackexchange.api.queries.site.sort.TagSort;
import org.thelq.stackexchange.api.queries.site.sort.TagSynonymSort;
import org.thelq.stackexchange.api.queries.site.sort.UserSort;

/**
 *
 * @author Leon
 */
@Getter
@RequiredArgsConstructor
public class SiteQueries {
	public static final SiteQueries DEFAULT = new SiteQueries(null, null, null);
	protected final String defaultSite;
	protected final String defaultAccessToken;
	protected final String defaultFilter;

	/**
	 * Get all answers on the site
	 * @see <a href="https://api.stackexchange.com/docs/answers">StackExchange API /answers usage documentation</a>
	 * @return applyDefaults(Generated configurable query
	 */
	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>> Q answersAll() {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>(AnswerEntry.class, new SimpleQueryMethod("answers"))
				.self());
	}

	/**
	 * Get answers that have the specified id
	 * @see <a href="https://api.stackexchange.com/docs/answers-by-ids">StackExchange API /answers/{} usage documentation</a>
	 * @param answerIds Non-null, non-empty collection of answer ids
	 * @return applyDefaults(Generated configurable query
	 */
	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>> Q answersByIds(Collection<Integer> answerIds) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>(AnswerEntry.class, new VectorQueryMethod("answers/{}", answerIds))
				.self());
	}

	/**
	 * Get all comments for the specified answer ids
	 * @see <a href="https://api.stackexchange.com/docs/comments-on-answers">StackExchange API /answers/{}/comments usage documentation</a>
	 * @param answerIds Non-null, non-empty collection of answer ids
	 * @return applyDefaults(Generated configurable query
	 */
	public <Q extends BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q answerComments(Collection<Integer> answerIds) {
		return applyDefaults(new BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("answers/{}/comments", answerIds))
				.self());
	}

	public <Q extends BaseComplexFullQuery<BadgeTypeSort<?>, Q, BadgeEntry>> Q badgesAll() {
		return applyDefaults(new BaseComplexFullQuery<BadgeTypeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<BadgeTypeSort<?>, Q, BadgeEntry>> Q badgesAll(@NonNull String inname) {
		return applyDefaults(new BaseComplexFullQuery<BadgeTypeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges"))
				.setParameter("inname", inname));
	}

	public <Q extends BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q badgesAllNamed() {
		return applyDefaults(new BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/name"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q badgesAllNamed(@NonNull String inname) {
		return applyDefaults(new BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/name"))
				.setParameter("inname", inname));
	}

	public <Q extends BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q badgesAllTagBased() {
		return applyDefaults(new BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/tags"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q badgesAllTagBased(@NonNull String inname) {
		return applyDefaults(new BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/tags"))
				.setParameter("inname", inname));
	}

	public <Q extends BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q badgesByIds(@NonNull Collection<Integer> badgeIds) {
		return applyDefaults(new BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, new VectorQueryMethod("badges/tags", badgeIds))
				.self());
	}

	public <Q extends BaseComplexDateQuery<Q, BadgeEntry>> Q badgeRecipients() {
		return applyDefaults(new BaseComplexDateQuery<Q, BadgeEntry>(BadgeEntry.class, new SimpleQueryMethod("badges/tags"))
				.self());
	}

	public <Q extends BaseComplexDateQuery<Q, BadgeEntry>> Q badgeRecipientsByIds(@NonNull Collection<Integer> badgeIds) {
		return applyDefaults(new BaseComplexDateQuery<Q, BadgeEntry>(BadgeEntry.class, new VectorQueryMethod("badges/tags", badgeIds))
				.self());
	}

	public <Q extends BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q commentsAll() {
		return applyDefaults(new BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, new SimpleQueryMethod("comments"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q commentsByIds(@NonNull Collection<Integer> commentIds) {
		return applyDefaults(new BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("comments/{}", commentIds))
				.self());
	}

	public <Q extends BaseSiteQuery<Q, CommentEntry>> Q commentEdit(int commentId, @NonNull String body) {
		return applyDefaults(new BaseSiteQuery<Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("comments/{}/edit", String.valueOf(commentId)))
				.setParameter("body", body)
				.setAuthRequired(true));
	}

	public <Q extends BaseSiteQuery<Q, CommentEntry>> Q commentDelete(int commentId) {
		return applyDefaults(new BaseSiteQuery<Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("comments/{}/delete", String.valueOf(commentId)))
				.setAuthRequired(true));
	}

	public <Q extends BaseSitePagableQuery<Q, EventEntry>> Q eventsAll() {
		return applyDefaults(new BaseSitePagableQuery<Q, EventEntry>(EventEntry.class, new SimpleQueryMethod("events"))
				.setAuthRequired(true));
	}

	public <Q extends BaseSitePagableQuery<Q, EventEntry>> Q eventsAll(@NonNull DateTime since) {
		return applyDefaults(new BaseSitePagableQuery<Q, EventEntry>(EventEntry.class, new SimpleQueryMethod("events"))
				.setParameter("since", String.valueOf(since.getMillis()))
				.setAuthRequired(true));
	}

	public <Q extends BaseSiteQuery<Q, InfoEntry>> Q info(@NonNull String site) {
		return applyDefaults(new BaseSiteQuery<Q, InfoEntry>(InfoEntry.class, new SimpleQueryMethod("info"))
				.setSite(site));
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, PostEntry>> Q postsAll() {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, PostEntry>(PostEntry.class, new SimpleQueryMethod("posts"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, PostEntry>> Q postsbyIds(@NonNull Collection<Integer> postIds) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, PostEntry>(PostEntry.class, new VectorQueryMethod("posts/{}", postIds))
				.self());
	}

	public <Q extends BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q postComment(@NonNull Collection<Integer> postIds) {
		return applyDefaults(new BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("posts/{}/comments", postIds))
				.self());
	}

	public <Q extends BaseSiteQuery<Q, CommentEntry>> Q postCommentAdd(int postId, @NonNull String body) {
		return applyDefaults(new BaseSiteQuery<Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("posts/{}/comments/add", String.valueOf(postId)))
				.setParameter("body", body)
				.setAuthRequired(true));
	}

	public <Q extends BaseComplexDateQuery<Q, RevisionEntry>> Q postRevisions(@NonNull Collection<Integer> postIds) {
		return applyDefaults(new BaseComplexDateQuery<Q, RevisionEntry>(RevisionEntry.class, new VectorQueryMethod("posts/{}/revisions", postIds))
				.self());
	}

	public <Q extends BaseComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>> Q postSuggestedEdits(@NonNull Collection<Integer> postIds) {
		return applyDefaults(new BaseComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>(SuggestedEditEntry.class, new VectorQueryMethod("posts/{}/suggested-edits", postIds))
				.self());
	}

	public <Q extends BaseSitePagableQuery<Q, PrivlegeEntry>> Q privilegesAll() {
		return applyDefaults(new BaseSitePagableQuery<Q, PrivlegeEntry>(PrivlegeEntry.class, new SimpleQueryMethod("privileges"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<QuestionTaggedSort<?>, Q, QuestionEntry>> Q questionsAll() {
		return applyDefaults(new BaseComplexFullQuery<QuestionTaggedSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<QuestionTaggedSort<?>, Q, QuestionEntry>> Q questionsByTags(@NonNull Collection<String> tags) {
		return applyDefaults(new BaseComplexFullQuery<QuestionTaggedSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags)));
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q questionsByIds(Collection<Integer> questionIds) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new VectorQueryMethod("questions/{}", questionIds))
				.self());
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q questionsByFeatured() {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/featured"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q questionsByFeatured(@NonNull Collection<String> tags) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/featured"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags)));
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q questionsByUnanswered() {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/unanswered"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q questionsByUnanswered(@NonNull Collection<String> tags) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/unanswered"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags)));
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q questionsByNoAnswers() {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/no-answers"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q questionsByNoAnswers(@NonNull Collection<String> tags) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, new SimpleQueryMethod("questions/no-answers"))
				.setParameter("tagged", QueryUtils.PARAMETER_JOINER.join(tags)));
	}

	public <Q extends BaseComplexFullQuery<QuestionRelatedSort<?>, Q, QuestionEntry>> Q questionsByLinked(Collection<Integer> questionIds) {
		return applyDefaults(new BaseComplexFullQuery<QuestionRelatedSort<?>, Q, QuestionEntry>(QuestionEntry.class, new VectorQueryMethod("questions/{}/linked", questionIds))
				.self());
	}

	public <Q extends BaseComplexFullQuery<QuestionRelatedSort<?>, Q, QuestionEntry>> Q questionsByRelated(Collection<Integer> questionIds) {
		return applyDefaults(new BaseComplexFullQuery<QuestionRelatedSort<?>, Q, QuestionEntry>(QuestionEntry.class, new VectorQueryMethod("questions/{}/related", questionIds))
				.self());
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>> Q questionAnswers(Collection<Integer> questionIds) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>(AnswerEntry.class, new VectorQueryMethod("questions/{}/answers", questionIds))
				.self());
	}

	public <Q extends BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q questionComments(Collection<Integer> questionIds) {
		return applyDefaults(new BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, new VectorQueryMethod("questions/{}/comments", questionIds))
				.self());
	}

	public <Q extends BaseComplexDateQuery<Q, QuestionTimelineEntry>> Q questionTimelines(Collection<Integer> questionIds) {
		return applyDefaults(new BaseComplexDateQuery<Q, QuestionTimelineEntry>(QuestionTimelineEntry.class, new VectorQueryMethod("questions/{}/timeline", questionIds))
				.self());
	}

	public <Q extends BaseComplexDateQuery<Q, RevisionEntry>> Q revisionsByIds(@NonNull Collection<String> revisionGuids) {
		Preconditions.checkArgument(revisionGuids.size() > 20, "Only 20 revision guids are supported by the StackExchange API, given %s", revisionGuids.size());
		return applyDefaults(new BaseComplexDateQuery<Q, RevisionEntry>(RevisionEntry.class, new VectorQueryMethod("revisions/{}", revisionGuids))
				.self());
	}

	public SearchBasicQuery searchBasic() {
		return applyDefaults(new SearchBasicQuery());
	}

	public SearchAdvancedQuery searchAdvanced() {
		return applyDefaults(new SearchAdvancedQuery());

	}

	public <Q extends BaseSearchQuery<Q>> Q searchSimilar(@NonNull String title) {
		return applyDefaults(new BaseSearchQuery<Q>(new SimpleQueryMethod("similar"))
				.setParameter("title", title));
	}

	public <Q extends BaseComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>> Q suggestedEditsAll() {
		return applyDefaults(new BaseComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>(SuggestedEditEntry.class, new SimpleQueryMethod("suggested-edits"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>> Q suggestedEditsByIds(@NonNull Collection<Integer> suggestedEditIds) {
		return applyDefaults(new BaseComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>(SuggestedEditEntry.class, new VectorQueryMethod("suggested-edits/{}", suggestedEditIds))
				.self());
	}

	public <Q extends BaseComplexFullQuery<TagSort<?>, Q, TagEntry>> Q tagsAll() {
		return applyDefaults(new BaseComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new SimpleQueryMethod("tags"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<TagSort<?>, Q, TagEntry>> Q tagsAll(@NonNull String inname) {
		return applyDefaults(new BaseComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new SimpleQueryMethod("tags"))
				.setParameter("inname", inname));
	}

	public <Q extends BaseComplexFullQuery<TagSort<?>, Q, TagEntry>> Q tagsAllModeratorOnly() {
		return applyDefaults(new BaseComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new SimpleQueryMethod("tags/moderator-only"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<TagSort<?>, Q, TagEntry>> Q tagsAllModeratorOnly(@NonNull String inname) {
		return applyDefaults(new BaseComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new SimpleQueryMethod("tags/moderator-only"))
				.setParameter("inname", inname));
	}

	public <Q extends BaseComplexFullQuery<TagSort<?>, Q, TagEntry>> Q tagsAllRequired() {
		return applyDefaults(new BaseComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new SimpleQueryMethod("tags/required"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<TagSort<?>, Q, TagEntry>> Q tagsAllRequired(@NonNull String inname) {
		return applyDefaults(new BaseComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new SimpleQueryMethod("tags/required"))
				.setParameter("inname", inname));
	}

	public <Q extends BaseComplexFullQuery<TagSynonymSort<?>, Q, TagSynonymEntry>> Q tagsAllSynonyms() {
		return applyDefaults(new BaseComplexFullQuery<TagSynonymSort<?>, Q, TagSynonymEntry>(TagSynonymEntry.class, new SimpleQueryMethod("tags/synonyms"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<TagSort<?>, Q, TagEntry>> Q tagsByNames(@NonNull Collection<String> tags) {
		return applyDefaults(new BaseComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, new VectorQueryMethod("tags/{}/info", tags))
				.self());
	}

	public <Q extends BaseSitePagableQuery<Q, QuestionEntry>> Q tagFaqs(@NonNull Collection<String> tags) {
		return applyDefaults(new BaseSitePagableQuery<Q, QuestionEntry>(QuestionEntry.class, new VectorQueryMethod("tags/{}/faq", tags))
				.self());
	}

	public <Q extends BaseSitePagableQuery<Q, TagEntry>> Q tagsRelated(@NonNull Collection<String> tags) {
		return applyDefaults(new BaseSitePagableQuery<Q, TagEntry>(TagEntry.class, new VectorQueryMethod("tags/{}/related", tags))
				.self());
	}

	public <Q extends BaseComplexFullQuery<TagSort<?>, Q, TagSynonymEntry>> Q tagSynonyms(@NonNull Collection<String> tags) {
		return applyDefaults(new BaseComplexFullQuery<TagSort<?>, Q, TagSynonymEntry>(TagSynonymEntry.class, new VectorQueryMethod("tags/{}/synonyms ", tags))
				.self());
	}

	public <Q extends BaseSitePagableQuery<Q, TagWikiEntry>> Q tagWikis(@NonNull Collection<String> tags) {
		return applyDefaults(new BaseSitePagableQuery<Q, TagWikiEntry>(TagWikiEntry.class, new VectorQueryMethod("tags/{}/wikis", tags))
				.self());
	}

	public <Q extends BaseSitePagableQuery<Q, TagEntry>> Q tagTopAnswers(@NonNull String tag, TagPeriod period) {
		return applyDefaults(new BaseSitePagableQuery<Q, TagEntry>(TagEntry.class, new VectorQueryMethod("tags/{}/top-answers/{}", tag, period.toString()))
				.self());
	}

	public <Q extends BaseSitePagableQuery<Q, TagEntry>> Q tagTopAskers(@NonNull String tag, TagPeriod period) {
		return applyDefaults(new BaseSitePagableQuery<Q, TagEntry>(TagEntry.class, new VectorQueryMethod("tags/{}/top-askers/{}", tag, period.toString()))
				.self());
	}

	public <Q extends BaseComplexFullQuery<UserSort<?>, Q, UserEntry>> Q usersAll() {
		return applyDefaults(new BaseComplexFullQuery<UserSort<?>, Q, UserEntry>(UserEntry.class, new SimpleQueryMethod("users"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<UserSort<?>, Q, UserEntry>> Q usersAll(String inname) {
		return applyDefaults(new BaseComplexFullQuery<UserSort<?>, Q, UserEntry>(UserEntry.class, new SimpleQueryMethod("users"))
				.setParameter("inname", inname));
	}

	public <Q extends BaseComplexFullQuery<UserSort<?>, Q, UserEntry>> Q usersByIds(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexFullQuery<UserSort<?>, Q, UserEntry>(UserEntry.class, UserQueryUtils.createUserQueryMethod("", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q userBadges(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, UserQueryUtils.createUserQueryMethod("badges", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>> Q userAnswers(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>(AnswerEntry.class, UserQueryUtils.createUserQueryMethod("answers", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q userComments(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, UserQueryUtils.createUserQueryMethod("comments", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q userCommentsTo(Collection<Integer> userIds, int told) {
		return applyDefaults(new BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, UserQueryUtils.createUserQueryMethod("comments/{}", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds))
				.setParameter("told", told));
	}

	public <Q extends BaseComplexFullQuery<QuestionFavoriteSort<?>, Q, QuestionEntry>> Q userFavorites(Collection<Integer> userIds, int told) {
		return applyDefaults(new BaseComplexFullQuery<QuestionFavoriteSort<?>, Q, QuestionEntry>(QuestionEntry.class, UserQueryUtils.createUserQueryMethod("favorites", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds))
				.setParameter("told", told));
	}

	public <Q extends BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q userMentioned(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, UserQueryUtils.createUserQueryMethod("mentioned", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseSitePagableQuery<Q, NotificationEntry>> Q userNotifications(Integer userId) {
		return applyDefaults(new BaseSitePagableQuery<Q, NotificationEntry>(NotificationEntry.class, UserQueryUtils.createUserQueryMethod("notifications", userId))
				.setAuthRequired(true));
	}

	public <Q extends BaseSitePagableQuery<Q, NotificationEntry>> Q userNotificationsUnread(Integer userId) {
		return applyDefaults(new BaseSitePagableQuery<Q, NotificationEntry>(NotificationEntry.class, UserQueryUtils.createUserQueryMethod("notifications/unread", userId))
				.setAuthRequired(true));
	}

	public <Q extends BaseSitePagableQuery<Q, PrivlegeEntry>> Q userPrivileges(Integer userId) {
		return applyDefaults(new BaseSitePagableQuery<Q, PrivlegeEntry>(PrivlegeEntry.class, UserQueryUtils.createUserQueryMethod("notifications/unread", userId))
				.setAuthRequired(UserQueryUtils.isMe(userId)));
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q userQuestions(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, UserQueryUtils.createUserQueryMethod("questions", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q userQuestionsFeatured(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, UserQueryUtils.createUserQueryMethod("questions/featured", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q userQuestionsUanswered(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, UserQueryUtils.createUserQueryMethod("questions/uanswered", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q userQuestionsNoAnswers(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, UserQueryUtils.createUserQueryMethod("questions/no-answers", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q userQuestionsUnaccepted(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, UserQueryUtils.createUserQueryMethod("questions/unaccepted", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseComplexDateQuery<Q, ReputationEntry>> Q userReputation(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexDateQuery<Q, ReputationEntry>(ReputationEntry.class, UserQueryUtils.createUserQueryMethod("reputation", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseSitePagableQuery<Q, ReputationHistoryEntry>> Q userReputationHistory(Collection<Integer> userIds) {
		return applyDefaults(new BaseSitePagableQuery<Q, ReputationHistoryEntry>(ReputationHistoryEntry.class, UserQueryUtils.createUserQueryMethod("reputation-history", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseSitePagableQuery<Q, ReputationHistoryEntry>> Q userReputationHistoryFull(Integer userId) {
		return applyDefaults(new BaseSitePagableQuery<Q, ReputationHistoryEntry>(ReputationHistoryEntry.class, UserQueryUtils.createUserQueryMethod("reputation-history/full", userId))
				.setAuthRequired(true));
	}

	public <Q extends BaseComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>> Q userSuggestedEdits(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>(SuggestedEditEntry.class, UserQueryUtils.createUserQueryMethod("suggested-edits", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseComplexFullQuery<TagSort<?>, Q, TagEntry>> Q userTags(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, UserQueryUtils.createUserQueryMethod("tags", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>> Q userTagsTopAnswers(Integer userId, Collection<String> tags) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, AnswerEntry>(AnswerEntry.class, UserQueryUtils.createUserQueryMethod("tags/{}/top-answers", userId, tags))
				.setAuthRequired(UserQueryUtils.isMe(userId)));
	}

	public <Q extends BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q userTagsTopQuestions(Integer userId, Collection<String> tags) {
		return applyDefaults(new BaseComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, UserQueryUtils.createUserQueryMethod("tags/{}/top-questions", userId, tags))
				.setAuthRequired(UserQueryUtils.isMe(userId)));
	}

	public <Q extends BaseComplexDateQuery<Q, UserTimelineEntry>> Q userTimeline(Collection<Integer> userIds) {
		return applyDefaults(new BaseComplexDateQuery<Q, UserTimelineEntry>(UserTimelineEntry.class, UserQueryUtils.createUserQueryMethod("timeline", userIds))
				.setAuthRequired(UserQueryUtils.isMe(userIds)));
	}

	public <Q extends BaseSitePagableQuery<Q, TopTagEntry>> Q userTopAnswerTags(Integer userId) {
		return applyDefaults(new BaseSitePagableQuery<Q, TopTagEntry>(TopTagEntry.class, UserQueryUtils.createUserQueryMethod("top-answer-tags", userId))
				.setAuthRequired(UserQueryUtils.isMe(userId)));
	}

	public <Q extends BaseSitePagableQuery<Q, TopTagEntry>> Q userTopQuestionTags(Integer userId) {
		return applyDefaults(new BaseSitePagableQuery<Q, TopTagEntry>(TopTagEntry.class, UserQueryUtils.createUserQueryMethod("top-question-tags", userId))
				.setAuthRequired(UserQueryUtils.isMe(userId)));
	}

	public <Q extends BaseSitePagableQuery<Q, WritePermissionEntry>> Q userWritePermissions(Integer userId) {
		return applyDefaults(new BaseSitePagableQuery<Q, WritePermissionEntry>(WritePermissionEntry.class, UserQueryUtils.createUserQueryMethod("write-permissions", userId))
				.setAuthRequired(UserQueryUtils.isMe(userId)));
	}

	public <Q extends BaseComplexFullQuery<UserSort<?>, Q, WritePermissionEntry>> Q userModerators() {
		return applyDefaults(new BaseComplexFullQuery<UserSort<?>, Q, WritePermissionEntry>(WritePermissionEntry.class, new SimpleQueryMethod("users/moderators"))
				.self());
	}

	public <Q extends BaseComplexFullQuery<UserSort<?>, Q, WritePermissionEntry>> Q userModeratorsElected() {
		return applyDefaults(new BaseComplexFullQuery<UserSort<?>, Q, WritePermissionEntry>(WritePermissionEntry.class, new SimpleQueryMethod("users/moderators"))
				.self());
	}

	public <Q extends BaseSitePagableQuery<Q, InboxItemEntry>> Q userInbox(int userId) {
		return applyDefaults(new BaseSitePagableQuery<Q, InboxItemEntry>(InboxItemEntry.class, UserQueryUtils.createUserQueryMethod("inbox", userId))
				.setAuthRequired(true));
	}

	public <Q extends BaseSitePagableQuery<Q, InboxItemEntry>> Q userInboxUnread(Integer userId) {
		return applyDefaults(new BaseSitePagableQuery<Q, InboxItemEntry>(InboxItemEntry.class, UserQueryUtils.createUserQueryMethod("inbox/unread", userId))
				.setAuthRequired(true));
	}

	public <Q extends BaseSitePagableQuery<Q, InboxItemEntry>> Q userInboxUnread(Integer userId, DateTime since) {
		return applyDefaults(new BaseSitePagableQuery<Q, InboxItemEntry>(InboxItemEntry.class, UserQueryUtils.createUserQueryMethod("inbox/unread", userId))
				.setAuthRequired(true)
				.setParameter("since", since.getMillis()));
	}

	protected <Q extends BaseSiteQuery<Q, ?>> Q applyDefaults(Q query) {
		if (defaultFilter != null)
			query.setFilter(defaultFilter);
		if (defaultAccessToken != null)
			query.setAccessToken(defaultAccessToken);
		if (defaultSite != null)
			query.setSite(defaultSite);
		return query;
	}
}
