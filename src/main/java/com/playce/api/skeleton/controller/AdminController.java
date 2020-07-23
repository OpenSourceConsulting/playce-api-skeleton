package com.playce.api.skeleton.controller;

import com.playce.api.skeleton.common.util.WebUtil;
import com.playce.api.skeleton.exception.NoPermissionException;
import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.Member;
import com.playce.api.skeleton.dto.PlayceMessage;
import com.playce.api.skeleton.dto.Status;
import com.playce.api.skeleton.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Jaeeon Bae
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/admin")
@Api(tags = {"Admin"}, description = "Rest APIs for Admin Menu")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private MemberService memberService;

    /**
     * <pre>
     * 등록된 사용자 목록 조회한다.
     * </pre>
     * @param pageable
     * @return
     */
    @ApiOperation(value = "사용자 목록 조회", notes = "사용자 목록을 조회한다.")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public PlayceMessage getMemberList(@ApiIgnore PlayceMessage message, @ApiIgnore @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        try {
            List<Member> memberList = memberService.getMemberList();

            /*
            // Administrator가 아닌 경우 자신과 같은 도메인의 사용자 목록만 조회
            if (!WebUtil.hasRole(1L)) {
                for (Iterator<Member> iterator = memberList.iterator(); iterator.hasNext(); ) {
                    Member member = iterator.next();

                    boolean hasPermission = false;
                    for (MembersRolesDomains mrd : member.getMembersRolesDomains()) {
                        hasPermission = false;
                        if (mrd.getDomainId() != null && WebUtil.hasReadPermission(mrd.getDomainId())) {
                            hasPermission = true;
                            break;
                        }
                    }

                    if (!hasPermission) {
                        iterator.remove();
                    }
                }
            }
            //*/

            for (Member member : memberList) {
                member.setPassword(null);
            }

            message.setStatus(Status.success);
            message.setData(memberList);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while fetch member list.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT fetch member list. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * <pre>
     * 등록된 사용자 상세 정보를 조회한다.
     * </pre>
     * @param id
     * @return
     */
    @ApiOperation(value = "사용자 상세 조회", notes = "사용자 상세 정보를 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Member ID", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public PlayceMessage getMemberDetail(@ApiIgnore PlayceMessage message, @PathVariable Long id) {
        try {
            /*
            // administrator가 아니거나 자기 자신의 정보 수정이 아닌경우
            if (!WebUtil.hasRole(1L) && WebUtil.getId().longValue() != id.longValue()) {
                message.setCode(403);
                throw new NoPermissionException("You don’t have permission to view user.");
            }
            //*/

            Member member = memberService.getMember(id);

            if (member == null) {
                message.setCode(404);
                throw new PlayceException("User does not exists.");
            }
            member.setPassword(null);

            message.setStatus(Status.success);
            message.setData(member);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while fetch member detail.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT fetch member detail. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * <pre>
     * 신규 사용자를 생성한다.
     * </pre>
     * @param message
     * @param member
     * @return
     */
    @ApiOperation(value = "사용자 생성", notes = "신규 사용자를 생성한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "member", value = "Member", required = true, dataType = "Member", paramType = "body")
    })
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public PlayceMessage createMember(@ApiIgnore PlayceMessage message, @RequestBody Member member) {
        try {
            // admin 권한 체크
            if (!WebUtil.hasRole(1L)) {
                message.setCode(403);
                throw new NoPermissionException("You don’t have permission to create user.");
            }

            // check duplicate user ID
            Member m = memberService.getMember(member.getUserId());

            if (m != null) {
                message.setCode(409);
                throw new PlayceException("User already exists.");
            }

            // create member
            memberService.createMember(member);

            member = memberService.getMember(member.getUserId());
            member.setPassword(null);

            message.setStatus(Status.success);
            message.setData(member);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while create member.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT create member. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * <pre>
     * 사용자를 수정한다.
     * </pre>
     * @param message
     * @param newMember
     * @return
     */
    @ApiOperation(value = "사용자 수정", notes = "사용자를 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newMember", value = "Member", required = true, dataType = "Member", paramType = "body")
    })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public PlayceMessage modifyMember(@ApiIgnore PlayceMessage message, @PathVariable Long id, @RequestBody Member newMember) {
        try {
            // admin 권한 체크
            if (!WebUtil.hasRole(1L)) {
                message.setCode(403);
                throw new NoPermissionException("You don’t have permission to edit user.");
            }

            Member originMember = memberService.getMember(id);

            if (originMember == null) {
                message.setCode(404);
                throw new PlayceException("User does not exists.");
            }
            newMember.setId(id);

            // update Member
            memberService.modifyMember(originMember, newMember);

            newMember = memberService.getMember(originMember.getUserId());
            newMember.setPassword(null);

            message.setStatus(Status.success);
            message.setData(newMember);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while update member.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT update member. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * <pre>
     * 등록된 사용자를 삭제한다.
     * </pre>
     * @param id
     * @return
     */
    @ApiOperation(value = "사용자 삭제", notes = "사용자를 삭제한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Member ID", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public PlayceMessage deleteMember(@ApiIgnore PlayceMessage message, @PathVariable long id) {
        try {
            // admin 권한 체크
            if (!WebUtil.hasRole(1L)) {
                message.setCode(403);
                throw new NoPermissionException("You don’t have permission to delete user.");
            }

            Member member = memberService.getMember(id);

            if (member == null) {
                message.setCode(404);
                throw new PlayceException("User does not exists.");
            }

            if (id == 1L) {
                message.setCode(405);
                throw new PlayceException("You can't delete default user.");
            }

            memberService.deleteMember(id);

            message.setStatus(Status.success);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while remove member.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT remove member. [Reason] : " + e.getMessage());
        }

        return message;
    }
}
//end of AdminController.java