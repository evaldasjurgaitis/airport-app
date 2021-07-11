import React from 'react';
import { Modal as ModalBase } from 'antd';
import { ModalProps } from 'antd/lib/modal';

import 'antd/lib/modal/style/index.less';

interface Props extends ModalProps {
    actions?: React.ReactNode;
    children?: React.ReactNode;
    onClose?: () => void;
    visible: boolean;
    title?: string;
    titleIcon?: React.ReactNode;
    width?: number;
    closable?: boolean;
    maskClosable?: boolean;
    size?: string;
    footerText?: string;
    footer?: React.ReactNode;
    overFlowScroll?: boolean;
}

const Modal = ({
    actions,
    children,
    onClose,
    visible,
    title,
    titleIcon,
    width = 700,
    closable = true,
    maskClosable = true,
    size,
    footerText,
    footer = false,
    overFlowScroll = false,
}: Props) => {
    const modalWith = size === 'sm' ? 500 : width;

    return (
        <ModalBase
            destroyOnClose
            title={title ? { title } : false}
            visible={visible}
            width={modalWith}
            onCancel={onClose}
            closable={closable}
            maskClosable={maskClosable}
            centered
            footer={footer}
        >
            {children}
        </ModalBase>
    );
};

export default Modal;
